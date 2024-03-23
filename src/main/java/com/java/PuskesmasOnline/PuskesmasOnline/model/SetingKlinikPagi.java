package com.java.PuskesmasOnline.PuskesmasOnline.model;

import javax.persistence.*;

@Entity
@Table(name = "setingKlinikPagi")
public class SetingKlinikPagi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jumlahNoAntrian")
    private String jumlahNoAntrian;

    @Column(name = "idKlinik")
    private String idKlinik;

    public SetingKlinikPagi(Long id, String jumlahNoAntrian, String idKlinik) {
        this.id = id;
        this.jumlahNoAntrian = jumlahNoAntrian;
        this.idKlinik = idKlinik;
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
}
