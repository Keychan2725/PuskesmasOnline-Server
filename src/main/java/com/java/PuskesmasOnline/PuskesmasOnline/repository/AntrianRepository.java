package com.java.PuskesmasOnline.PuskesmasOnline.repository;

import com.java.PuskesmasOnline.PuskesmasOnline.model.Antrian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AntrianRepository extends JpaRepository<Antrian , Long> {

    List<Antrian> findAllByIdUser (String idUer);
    Optional<Antrian> findByidUser(String idUser);



}
