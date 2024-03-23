package com.java.PuskesmasOnline.PuskesmasOnline.repository;

import com.java.PuskesmasOnline.PuskesmasOnline.model.DataKlinik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataKlinikRepository extends JpaRepository<DataKlinik , Long> {


}
