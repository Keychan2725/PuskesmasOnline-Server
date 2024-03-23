package com.java.PuskesmasOnline.PuskesmasOnline.repository;


import com.java.PuskesmasOnline.PuskesmasOnline.model.Ruangan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuanganRepository extends JpaRepository<Ruangan , Long> {
}
