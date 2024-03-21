package com.java.PuskesmasOnline.PuskesmasOnline.service;
import com.java.PuskesmasOnline.PuskesmasOnline.exception.NotFoundException;
import com.java.PuskesmasOnline.PuskesmasOnline.repository.UserRepository;
import com.java.PuskesmasOnline.PuskesmasOnline.security.JwtUtils;
import com.java.PuskesmasOnline.PuskesmasOnline.model.LoginRequest;
import com.java.PuskesmasOnline.PuskesmasOnline.model.User;
import com.java.PuskesmasOnline.PuskesmasOnline.util.EmailUtil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserImpl implements UserService{

    @Autowired
    private UserRepository userRepository;



    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder encoder;


    @Autowired
    AuthenticationManager authenticationManager;


    @Override
    public Map<Object, Object> login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new NotFoundException("Username not found"));

        if (encoder.matches(loginRequest.getPassword(), user.getPassword())) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            // Lakukan validasi dan pengalihan berdasarkan peran
            return redirectAndValidate(authentication, user);
        }

        throw new NotFoundException("Invalid role or password");
    }

    private Map<Object, Object> redirectAndValidate(Authentication authentication, User user) {
        String jwt = jwtUtils.generateToken(authentication);
        user.setLast_login(new Date());
        userRepository.save(user);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedLastLogin = sdf.format(user.getLast_login());

        Map<Object, Object> response = new HashMap<>();
        response.put("data", user);
        response.put("id", user.getId());
        response.put("token", jwt);
        response.put("last_login", formattedLastLogin);

        if (!user.getCodeVer().equals(null)){
            // Lakukan pengalihan berdasarkan peran
            if (user.getRole().equals("admin")) {
                response.put("type_token", "Admin");

            } else if (user.getRole().equals("user")) {
                response.put("type_token", "User");

            } else if (user.getRole().equals("super_admin")) {
                response.put("type_token", "Super Admin");

            } else {
                throw new NotFoundException("Invalid role");
            }
        } else {
                 throw new NotFoundException("Akun Anda Belum Terverifikasi");
        }

        return response;
    }
    @Override
    public User addUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new NotFoundException("Email sudah digunakan");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("user");
        return userRepository.save(user);
    }



    @Override
    public User addAdmin(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new NotFoundException("Email sudah digunakan");
        }


        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("admin");
        return userRepository.save(user);
    }

    @Override
    public User get(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("Id Not Found"));
    }

    @Override
    public String forgotPassword(String email) throws MessagingException, UnsupportedEncodingException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new RuntimeException("User Tidak Ditemukan Dengan Email : " + email)
                );

        try {

            EmailUtil.sendSetPassword(email);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send set password ");
        }


        return "Tolong Cek Email Anda Untuk Melakukan Forgot Password";
    }


    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User edit(Long id, User user) {
        // Temukan user yang ingin diubah
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User tidak ditemukan"));


        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());

        // Set password yang sudah di-hash ke existingUser
        existingUser.setPassword(encodedPassword);
        existingUser.setUsername(user.getUsername());
        existingUser.setNoTel(user.getNoTel());
        existingUser.setImgUser(user.getImgUser());
        existingUser.setEmail(user.getEmail());
        // Tambahkan fields lain yang ingin diubah

        // Simpan perubahan ke database, tanpa membuat object baru
        return userRepository.save(existingUser);
    }



    @Override
    public Map<String, Boolean> delete(Long id) {
        try {
            userRepository.deleteById(id);
            Map<String, Boolean> res = new HashMap<>();
            res.put("Deleted", Boolean.TRUE);
            return res;
        } catch (Exception e) {
            return null;
        }
    }
}