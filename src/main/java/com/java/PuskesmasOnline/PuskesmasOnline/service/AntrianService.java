package com.java.PuskesmasOnline.PuskesmasOnline.service;


import com.java.PuskesmasOnline.PuskesmasOnline.model.Antrian;
import com.java.PuskesmasOnline.PuskesmasOnline.model.KlinikPagi;
import com.java.PuskesmasOnline.PuskesmasOnline.repository.AntrianRepository;
import com.java.PuskesmasOnline.PuskesmasOnline.repository.KlinikPagiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AntrianService {

    @Autowired
    private AntrianRepository antrianRepository;


    @Autowired
    private KlinikPagiRepository klinikPagiRepository;

    public Optional<Antrian> getAntrianByUserId (String idUser){
        return antrianRepository.findByidUser(idUser);
    }
    public List<Antrian> getAllAntrian() {
        return antrianRepository.findAll();
    }


    public Optional<Antrian> getAntrianById(Long id) {
        return antrianRepository.findById(id);
    }
    public List<Antrian> getAllAntrianByIdUser(String idUser) {
        return antrianRepository.findAllByIdUser(idUser);
    }

    public Antrian saveOrUpdateAntrian(Antrian antrian) {
        return antrianRepository.save(antrian);
    }


    public void deleteAntrian(Long id) {
        antrianRepository.deleteById(id);
    }


}
