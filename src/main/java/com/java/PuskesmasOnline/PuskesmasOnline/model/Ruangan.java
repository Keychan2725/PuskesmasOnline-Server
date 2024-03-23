package com.java.PuskesmasOnline.PuskesmasOnline.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ruangan")
public class Ruangan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "namaRuangan")
    private String namaRuangan;

    @Column(name = "namaPasien")
    private String namaPasien;

    @Column(name = "pembayaran")
    private String pembayaran;

    @Column(name = "idOperasi")
    private String idOperasi;

    @Column(name = "status")
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Jakarta")
    @Column(name = "tanggalWaktu")
    private Date tanggalWaktu;

    public Ruangan(Long id, String namaRuangan, String namaPasien, String pembayaran, String idOperasi, String status, Date tanggalWaktu) {
        this.id = id;
        this.namaRuangan = namaRuangan;
        this.namaPasien = namaPasien;
        this.pembayaran = pembayaran;
        this.idOperasi = idOperasi;
        this.status = status;
        this.tanggalWaktu = tanggalWaktu;
    }

    public Ruangan() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaRuangan() {
        return namaRuangan;
    }

    public void setNamaRuangan(String namaRuangan) {
        this.namaRuangan = namaRuangan;
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

    public String getIdOperasi() {
        return idOperasi;
    }

    public void setIdOperasi(String idOperasi) {
        this.idOperasi = idOperasi;
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
