package com.java.PuskesmasOnline.PuskesmasOnline.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "setingKlinikPagi")
public class SetingKlinikPagi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jumlahNoAntrian")
    private String jumlahNoAntrian;


    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Jakarta")
    @Column(name = "tanggalWaktu")
    private Date tanggalWaktu;
    @Column(name = "idKlinik")
    private String idKlinik;

    public SetingKlinikPagi( ) {

    }

    public SetingKlinikPagi(Long id, String jumlahNoAntrian, String idKlinik , Date tanggalWaktu) {
        this.id = id;
        this.jumlahNoAntrian = jumlahNoAntrian;
        this.idKlinik = idKlinik;
        this.tanggalWaktu = tanggalWaktu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJumlahNoAntrian() {
        return jumlahNoAntrian;
    }

    public void setJumlahNoAntrian(String jumlahNoAntrian) {
        this.jumlahNoAntrian = jumlahNoAntrian;
    }

    public String getIdKlinik() {
        return idKlinik;
    }

    public void setIdKlinik(String idKlinik) {
        this.idKlinik = idKlinik;
    }

    public Date getTanggalWaktu() {
        return tanggalWaktu;
    }

    public void setTanggalWaktu(Date tanggalWaktu) {
        this.tanggalWaktu = tanggalWaktu;
    }
}
