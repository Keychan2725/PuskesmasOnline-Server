package com.java.PuskesmasOnline.PuskesmasOnline.repository;

import com.java.PuskesmasOnline.PuskesmasOnline.model.KlinikPagi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KlinikPagiRepository extends JpaRepository<KlinikPagi , Long> {
    List<KlinikPagi> findAllByKlinikId(String klinikId);

    List<KlinikPagi> findAntrianByKlinikId (String klinikId);
    Optional<KlinikPagi> findByKlinikId (String klinikId);
    List<KlinikPagi> getAllByKlinikId (String klinikId);
    Optional<KlinikPagi> findById (Long id);



}
