package com.java.PuskesmasOnline.PuskesmasOnline.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "operasi")
public class Operasi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "namaPasien")
    private String namaPasien;

    @Column(name = "namaOperasi")
    private String nameOperasi;

    @Column(name = "idRuangan")
    private String idRuangan;

    @Column(name = "idPasien")
    private String idPasien;

    @Column(name = "idKlinik")
    private String idKlinik;

    @Column(name = "status")
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Jakarta")
    @Column(name = "tanggalWaktu")
    private Date tanggalWaktu;


    public Operasi(Long id, String idRuangan, String namaPasien, String nameOperasi, String idPasien, String idKlinik, String status, Date tanggalWaktu) {
        this.id = id;
        this.namaPasien = namaPasien;
        this.idRuangan = idRuangan;
        this.nameOperasi = nameOperasi;
        this.idPasien = idPasien;
        this.idKlinik = idKlinik;
        this.status = status;
        this.tanggalWaktu = tanggalWaktu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getNameOperasi() {
        return nameOperasi;
    }

    public void setNameOperasi(String nameOperasi) {
        this.nameOperasi = nameOperasi;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getIdKlinik() {
        return idKlinik;
    }

    public String getIdRuangan() {
        return idRuangan;
    }

    public void setIdRuangan(String idRuangan) {
        this.idRuangan = idRuangan;
    }

    public void setIdKlinik(String idKlinik) {
        this.idKlinik = idKlinik;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTanggalWaktu() {
        return tanggalWaktu;
    }

    public void setTanggalWaktu(Date tanggalWaktu) {
        this.tanggalWaktu = tanggalWaktu;
    }
}
