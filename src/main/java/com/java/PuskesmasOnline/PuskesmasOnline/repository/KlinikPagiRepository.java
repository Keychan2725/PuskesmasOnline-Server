package com.java.PuskesmasOnline.PuskesmasOnline.repository;

import com.java.PuskesmasOnline.PuskesmasOnline.model.KlinikPagi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KlinikPagiRepository extends JpaRepository<KlinikPagi , Long> {
}
