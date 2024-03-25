package com.java.PuskesmasOnline.PuskesmasOnline.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservasiOperasi")
public class ReservasiOperasi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "namaOperasi")
    private String nameOperasi;

    @Column(name = "namaPasien")
    private String namaPasien;

    @Column(name = "pembayaran")
    private String pembayaran;


    @Column(name = "idRuangan")
    private String idRuangan;

    @Column(name = "status")
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Jakarta")
    @Column(name = "tanggalWaktu")
    private Date tanggalWaktu;

    public ReservasiOperasi() {

    }

    public ReservasiOperasi(Long id, String nameOperasi, String namaPasien, String pembayaran, String idRuangan, String status, Date tanggalWaktu) {
        this.id = id;
        this.nameOperasi = nameOperasi;
        this.namaPasien = namaPasien;
        this.pembayaran = pembayaran;
        this.idRuangan = idRuangan;
        this.status = status;
        this.tanggalWaktu = tanggalWaktu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOperasi() {
        return nameOperasi;
    }

    public void setNameOperasi(String nameOperasi) {
        this.nameOperasi = nameOperasi;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getPembayaran() {
        return pembayaran;
    }

    public void setPembayaran(String pembayaran) {
        this.pembayaran = pembayaran;
    }

    public String getIdRuangan() {
        return idRuangan;
    }

    public void setIdRuangan(String idRuangan) {
        this.idRuangan = idRuangan;
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
