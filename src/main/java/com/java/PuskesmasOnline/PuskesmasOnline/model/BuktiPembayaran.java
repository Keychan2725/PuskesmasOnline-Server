package com.java.PuskesmasOnline.PuskesmasOnline.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "buktiPembayaran")
public class BuktiPembayaran {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idPasien")
    private String idPasien;

    @Column(name = "idKlinik")
    private String idKlinik;

    @Column(name = "img")
    private String img;

    @Column(name = "status")
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Jakarta")
    @Column(name = "tanggalWaktu")
    private Date tanggalWaktu;

    public BuktiPembayaran() {

    }

    public BuktiPembayaran(Long id, String idPasien, String idKlinik, String img, String status, Date tanggalWaktu) {
        this.id = id;
        this.idPasien = idPasien;
        this.idKlinik = idKlinik;
        this.img = img;
        this.status = status;
        this.tanggalWaktu = tanggalWaktu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setIdKlinik(String idKlinik) {
        this.idKlinik = idKlinik;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
