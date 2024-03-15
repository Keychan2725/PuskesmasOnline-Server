package com.java.PuskesmasOnline.PuskesmasOnline.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.java.PuskesmasOnline.PuskesmasOnline.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

public class UserDetail implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Long id;

    private String email;

    private String username;

    private String status;

    private String codeVer;

    private String imgUser;
    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;


    public UserDetail() {

    }

    public UserDetail(Long id, String email, String password , String username , String imgUser,String status, String codeVer) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.imgUser = imgUser;
        this.status = status;
        this.codeVer = codeVer;
    }

    public static UserDetail buildUser(User admin) {
        return new UserDetail(
                admin.getId(),
                admin.getEmail(),
                admin.getPassword(),
                admin.getUsername(),
                admin.getImgUser(),
                admin.getStatus(),
                admin.getCodeVer());

    }


    public Long getId() {
        return id;
    }



    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }


    public String getImgUser() {
        return imgUser;
    }

    public void setImgUser(String imgUser) {
        this.imgUser = imgUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCodeVer() {
        return codeVer;
    }

    public void setCodeVer(String codeVer) {
        this.codeVer = codeVer;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetail admin = (UserDetail) o;
        return Objects.equals(id, admin.id);
    }

}