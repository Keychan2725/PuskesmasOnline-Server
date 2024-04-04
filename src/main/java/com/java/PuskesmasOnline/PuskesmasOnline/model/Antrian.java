package com.java.PuskesmasOnline.PuskesmasOnline.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.java.PuskesmasOnline.PuskesmasOnline.repository.AntrianRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "antrian")
public class Antrian {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idKlinik")
    private String idKlinik;



    @Column(name = "idUser")
    private String idUser;

    @Column(name = "namaKlinik")
    private  String namaKlinik;
    @Column(name = "noAntrian")
    private String noAntrian;

    @Column(name = "status")
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Jakarta")
    @Column(name = "tanggalWaktu")
    private Date tanggalWaktu;


    public Antrian() {
    }

    public Antrian(Long id, String idKlinik, String noAntrian,String namaKlinik ,  String idUser, String status , Date tanggalWaktu) {
        this.id = id;
        this.idKlinik = idKlinik;
        this.idUser = idUser;
        this.status = status;
        this.namaKlinik = namaKlinik;
        this.noAntrian = noAntrian;
        this.tanggalWaktu = tanggalWaktu;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdKlinik() {
        return idKlinik;
    }

    public void setIdKlinik(String idKlinik) {

        this.idKlinik = idKlinik;
    }
    public String getNamaKlinik() {
        return namaKlinik;
    }

    public void setNamaKlinik(String namaKlinik) {
        this.namaKlinik = namaKlinik;
    }



    public String getNoAntrian() {
        return noAntrian;
    }

    public void setNoAntrian(String noAntrian) {
        this.noAntrian = noAntrian;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
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
