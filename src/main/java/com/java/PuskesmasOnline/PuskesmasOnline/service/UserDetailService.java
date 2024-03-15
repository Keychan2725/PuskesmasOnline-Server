package com.java.PuskesmasOnline.PuskesmasOnline.service;
import com.java.PuskesmasOnline.PuskesmasOnline.exception.NotFoundException;
import com.java.PuskesmasOnline.PuskesmasOnline.repository.UserRepository;
import com.java.PuskesmasOnline.PuskesmasOnline.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        if (userRepository.existsByEmail(username)){
            User user = userRepository.findByEmail(username).orElseThrow(() -> new NotFoundException("Id Not Found"));
            return UserDetail.buildUser(user);
        }
        throw new NotFoundException("Entity not found");
    }

}