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

    @Column(name = "statusklinik")
    private String statusKlinik;

    @Column(name = "klinikId")
    private String klinikId;

    @Column(name = "namaKlinik")
    private String namaKlinik;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "status")
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Jakarta")
    @Column(name = "tanggalWaktu")
    private Date tanggalWaktu;

    public KlinikPagi( ) {

    }



    public KlinikPagi(Long id, String noAntrian, String status , String klinikId, String namaKlinik, Date tanggalWaktu ,String alamat ,String statusKlinik) {
        this.id = id;
        this.noAntrian = noAntrian;
        this.klinikId = klinikId ;
        this.namaKlinik = namaKlinik;
        this.tanggalWaktu = tanggalWaktu;
        this.statusKlinik = statusKlinik;
        this.alamat = alamat;
        this.status = status;
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


    public String getKlinikId() {
        return klinikId;
    }

    public void setKlinikId(String klinikId) {
        this.klinikId = klinikId;
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

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setTanggalWaktu(Date tanggalWaktu) {
        this.tanggalWaktu = tanggalWaktu;
    }

    public String getStatusKlinik() {
        return statusKlinik;
    }

    public void setStatusKlinik(String statusKlinik) {
        this.statusKlinik = statusKlinik;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
