package com.java.PuskesmasOnline.PuskesmasOnline.repository;

import com.java.PuskesmasOnline.PuskesmasOnline.model.Reset_Password;
import com.java.PuskesmasOnline.PuskesmasOnline.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Reset_PasswordRepository extends JpaRepository<Reset_Password, Long> {


    Optional<Reset_Password> findByEmail(String email);

    Boolean existsByEmail(String email);
}


