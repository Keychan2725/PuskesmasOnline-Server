package com.java.PuskesmasOnline.PuskesmasOnline.repository;

import com.java.PuskesmasOnline.PuskesmasOnline.model.ReservasiOperasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservasiOperasiRepository extends JpaRepository<ReservasiOperasi , Long> {

}
