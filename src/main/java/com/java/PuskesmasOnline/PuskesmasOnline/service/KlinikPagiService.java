package com.java.PuskesmasOnline.PuskesmasOnline.service;


import com.java.PuskesmasOnline.PuskesmasOnline.model.KlinikPagi;
import com.java.PuskesmasOnline.PuskesmasOnline.repository.KlinikPagiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class KlinikPagiService  {

    @Autowired
    private KlinikPagiRepository klinikPagiRepository;

    // Create operation
    public KlinikPagi createOrUpdate(KlinikPagi klinikPagi) {
        Date currentDate = new Date(); // Mendapatkan waktu saat ini
        klinikPagi.setTanggalWaktu(currentDate);
        return klinikPagiRepository.save(klinikPagi);
    }

    public List<KlinikPagi> getAllKlinikPagi() {
        return klinikPagiRepository.findAll();
    }

    public List<KlinikPagi> getKlinikPagiByKlinikId(String klinikId) {
        return klinikPagiRepository.findAllByKlinikId (klinikId);
    }

    public Optional<KlinikPagi> getById (Long id) {
        return klinikPagiRepository.findById(id);
    }

    // Update operation
    public KlinikPagi updateKlinikPagi(Long id, KlinikPagi newKlinikPagi) {
        Optional<KlinikPagi> existingKlinikPagiOptional = klinikPagiRepository.findById(id);
        if (existingKlinikPagiOptional.isPresent()) {
            KlinikPagi existingKlinikPagi = existingKlinikPagiOptional.get();
            existingKlinikPagi.setNoAntrian(newKlinikPagi.getNoAntrian());
            existingKlinikPagi.setKlinikId(newKlinikPagi.getKlinikId());
            existingKlinikPagi.setNamaKlinik(newKlinikPagi.getNamaKlinik());
            existingKlinikPagi.setTanggalWaktu(newKlinikPagi.getTanggalWaktu());
            existingKlinikPagi.setStatusKlinik(newKlinikPagi.getStatusKlinik());
            existingKlinikPagi.setAlamat(newKlinikPagi.getAlamat());
            existingKlinikPagi.setStatus(newKlinikPagi.getStatus());
            return klinikPagiRepository.save(existingKlinikPagi);
        } else {
            // Handle case when klinikPagi with given id not found
            return null;
        }
    }

    // Delete operation
    public void deleteKlinikPagi(Long id) {
        klinikPagiRepository.deleteById(id);
    }



}
