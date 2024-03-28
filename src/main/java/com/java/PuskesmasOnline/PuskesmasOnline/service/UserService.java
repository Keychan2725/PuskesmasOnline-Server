package com.java.PuskesmasOnline.PuskesmasOnline.service;

import com.java.PuskesmasOnline.PuskesmasOnline.dto.ForGotPass;
import com.java.PuskesmasOnline.PuskesmasOnline.model.LoginRequest;
import com.java.PuskesmasOnline.PuskesmasOnline.model.User;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public interface UserService {

    Map<Object, Object> login(LoginRequest loginRequest);
    User addUser(User user);

    User addAdmin(User user);
    User get(Long id);



    List<User> getAll();


    User edit(Long id, User user);

    Map<String, Boolean> delete(Long id);
}