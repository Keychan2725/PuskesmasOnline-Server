package com.java.PuskesmasOnline.PuskesmasOnline.model;

import javax.persistence.*;

@Entity
@Table(name = "antrian")
public class Antrian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idKlinik")
    private String idKlinik;

    @Column(name = "idKlinikPagi")
    private String idKlinikPagi;

    @Column(name = "idUser")
    private String idUser;

    @Column(name = "status")
    private String status;


    public Antrian() {
    }

    public Antrian(Long id, String idKlinik, String idKlinikPagi, String idUser, String status) {
        this.id = id;
        this.idKlinik = idKlinik;
        this.idKlinikPagi = idKlinikPagi;
        this.idUser = idUser;
        this.status = status;
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

    public String getIdKlinikPagi() {
        return idKlinikPagi;
    }

    public void setIdKlinikPagi(String idKlinikPagi) {
        this.idKlinikPagi = idKlinikPagi;
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
}
