package com.java.PuskesmasOnline.PuskesmasOnline.model;


import javax.persistence.*;

@Entity
@Table(name = "dataKlinik")
public class DataKlinik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "namaklinik")
    private String namaKlinik;

    @Column(name = "alamatKlinik")
    private String alamatKlinik;

    @Column(name = "pemilik")
    private String pemilik;

    @Column(name = "idUser")
    private String idUser;

    public DataKlinik( ) {

    }

    public DataKlinik(Long id, String idUser , String namaKlinik, String alamatKlinik, String pemilik) {
        this.id = id;
        this.idUser = idUser;
        this.namaKlinik = namaKlinik;
        this.alamatKlinik = alamatKlinik;
        this.pemilik = pemilik;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaKlinik() {
        return namaKlinik;
    }

    public void setNamaKlinik(String namaKlinik) {
        this.namaKlinik = namaKlinik;
    }

    public String getAlamatKlinik() {
        return alamatKlinik;
    }

    public void setAlamatKlinik(String alamatKlinik) {
        this.alamatKlinik = alamatKlinik;
    }

    public String getPemilik() {
        return pemilik;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setPemilik(String pemilik) {
        this.pemilik = pemilik;
    }


}
