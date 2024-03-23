package com.java.PuskesmasOnline.PuskesmasOnline.model;

import javax.persistence.*;

@Entity
@Table(name = "dataDiri")
public class DataDiri {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "namaDepan")
    private String namaDepan;

    @Column(name = "namaBelakang")
    private String namaBelakang;

    @Column(name = "usia")
    private String usia;

    @Column(name = "gender")
    private String gender;

    @Column(name = "idUser")
    private String idUser;

    @Column(name = "nik")
    private String nik;


    public DataDiri(Long id, String idUser ,String namaDepan, String namaBelakang, String usia, String gender, String nik) {
        this.id = id;
        this.idUser = idUser;
        this.namaDepan = namaDepan;
        this.namaBelakang = namaBelakang;
        this.usia = usia;
        this.gender = gender;
        this.nik = nik;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaDepan() {
        return namaDepan;
    }

    public void setNamaDepan(String namaDepan) {
        this.namaDepan = namaDepan;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNamaBelakang() {
        return namaBelakang;
    }

    public void setNamaBelakang(String namaBelakang) {
        this.namaBelakang = namaBelakang;
    }

    public String getUsia() {
        return usia;
    }

    public void setUsia(String usia) {
        this.usia = usia;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }
}
