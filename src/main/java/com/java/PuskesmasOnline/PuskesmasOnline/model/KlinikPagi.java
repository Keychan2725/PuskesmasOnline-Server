package com.java.PuskesmasOnline.PuskesmasOnline.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "klinikPagi")
public class KlinikPagi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "noAntrian")
    private String noAntrian;

    @Column(name = "status")
    private String status;

    @Column(name = "namaKlinik")
    private String namaKlinik;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Jakarta")
    @Column(name = "tanggalWaktu")
    private Date tanggalWaktu;


    public KlinikPagi(Long id, String noAntrian, String status, String namaKlinik, Date tanggalWaktu) {
        this.id = id;
        this.noAntrian = noAntrian;
        this.status = status;
        this.namaKlinik = namaKlinik;
        this.tanggalWaktu = tanggalWaktu;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoAntrian() {
        return noAntrian;
    }

    public void setNoAntrian(String noAntrian) {
        this.noAntrian = noAntrian;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNamaKlinik() {
        return namaKlinik;
    }

    public void setNamaKlinik(String namaKlinik) {
        this.namaKlinik = namaKlinik;
    }

    public Date getTanggalWaktu() {
        return tanggalWaktu;
    }

    public void setTanggalWaktu(Date tanggalWaktu) {
        this.tanggalWaktu = tanggalWaktu;
    }
}
