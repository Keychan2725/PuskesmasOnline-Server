package com.java.PuskesmasOnline.PuskesmasOnline.model;


import javax.persistence.*;

@Entity
@Table(name = "dataKlinik")
public class DataKlinik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "namaKlinik")
    private String namaklinik;

    @Column(name = "alamatKlinik")
    private String alamatKlinik;

    @Column(name = "pemilik")
    private String pemilik;

    @Column(name = "idUser")
    private String idUser;
    @Column(name = "operasiClient")
    private String operasiClient;

    public DataKlinik(Long id, String idUser ,String namaklinik, String alamatKlinik, String pemilik, String operasiClient) {
        this.id = id;
        this.idUser = idUser;
        this.namaklinik = namaklinik;
        this.alamatKlinik = alamatKlinik;
        this.pemilik = pemilik;
        this.operasiClient = operasiClient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaklinik() {
        return namaklinik;
    }

    public void setNamaklinik(String namaklinik) {
        this.namaklinik = namaklinik;
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

    public String getOperasiClient() {
        return operasiClient;
    }

    public void setOperasiClient(String operasiClient) {
        this.operasiClient = operasiClient;
    }
}
