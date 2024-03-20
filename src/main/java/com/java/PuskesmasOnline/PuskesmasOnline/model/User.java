package com.java.PuskesmasOnline.PuskesmasOnline.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

import javax.persistence.*;



@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password" , unique = true)
    private String password;

   @Column(name = "username")
   private  String username;

    @Column(name = "codeVer"  )
    private  String codeVer;

    @Column(name = "imgUser")
    private  String imgUser;

    @Column(name = "noTel")
    private String noTel;

    @Column(name = "status")
    private String status;

    private boolean enabled;
    @Column(name = "role")
    private String role;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Jakarta")
    @Column(name = "last_login")
    private Date last_login;


    public Long getId() {
        return id;
    }

    public String getNoTel() {
        return noTel;
    }

    public void setNoTel(String noTel) {
        this.noTel = noTel;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getImgUser() {
        return imgUser;
    }

    public void setImgUser(String imgUser) {
        this.imgUser = imgUser;
    }


}