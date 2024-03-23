package com.java.PuskesmasOnline.PuskesmasOnline.repository;

import com.java.PuskesmasOnline.PuskesmasOnline.model.DataDiri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataDiriRepository extends JpaRepository<DataDiri , Long> {

}
