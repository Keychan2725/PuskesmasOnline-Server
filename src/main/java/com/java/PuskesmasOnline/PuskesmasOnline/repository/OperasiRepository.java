package com.java.PuskesmasOnline.PuskesmasOnline.repository;

import com.java.PuskesmasOnline.PuskesmasOnline.model.Operasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperasiRepository extends JpaRepository<Operasi ,Long> {
}
