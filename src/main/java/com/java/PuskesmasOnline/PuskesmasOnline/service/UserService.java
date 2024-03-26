package com.java.PuskesmasOnline.PuskesmasOnline.service;

import com.java.PuskesmasOnline.PuskesmasOnline.dto.ForGotPass;
import com.java.PuskesmasOnline.PuskesmasOnline.model.LoginRequest;
import com.java.PuskesmasOnline.PuskesmasOnline.model.User;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public interface UserService {
    ForGotPass sendPassword(ForGotPass forGotPass) throws javax.mail.MessagingException, MessagingException;

    Map<Object, Object> login(LoginRequest loginRequest);
    User addUser(User user);

    User addAdmin(User user);
    User get(Long id);

    ForGotPass sendEmail(ForGotPass forGotPass) throws javax.mail.MessagingException, MessagingException;

    User updatePassword(Long id, User user);

    String forgotPassword(String email) throws MessagingException, UnsupportedEncodingException;

    List<User> getAll();


    User edit(Long id, User user);

    Map<String, Boolean> delete(Long id);
}