package com.java.PuskesmasOnline.PuskesmasOnline.service;


import com.java.PuskesmasOnline.PuskesmasOnline.model.ReservasiOperasi;
import com.java.PuskesmasOnline.PuskesmasOnline.repository.ReservasiOperasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservasiOperasiService {

    @Autowired
    private ReservasiOperasiRepository reservasiOperasiRepository;

    // Create operation
    public ReservasiOperasi createOrUpdate(ReservasiOperasi reservasiOperasi) {
        return reservasiOperasiRepository.save(reservasiOperasi);
    }

    // Read operation
    public List<ReservasiOperasi> getAllReservasiOperasi() {
        return reservasiOperasiRepository.findAll();
    }

    public Optional<ReservasiOperasi> getReservasiOperasiById(Long id) {
        return reservasiOperasiRepository.findById(id);
    }

    // Update operation
    public ReservasiOperasi updateReservasiOperasi(Long id, ReservasiOperasi newReservasiOperasi) {
        Optional<ReservasiOperasi> existingReservasiOperasiOptional = reservasiOperasiRepository.findById(id);
        if (existingReservasiOperasiOptional.isPresent()) {
            ReservasiOperasi existingReservasiOperasi = existingReservasiOperasiOptional.get();
            existingReservasiOperasi.setNameOperasi(newReservasiOperasi.getNameOperasi());
            existingReservasiOperasi.setNamaPasien(newReservasiOperasi.getNamaPasien());
            existingReservasiOperasi.setPembayaran(newReservasiOperasi.getPembayaran());
            existingReservasiOperasi.setIdRuangan(newReservasiOperasi.getIdRuangan());
            existingReservasiOperasi.setStatus(newReservasiOperasi.getStatus());
            existingReservasiOperasi.setTanggalWaktu(newReservasiOperasi.getTanggalWaktu());
            return reservasiOperasiRepository.save(existingReservasiOperasi);
        } else {
            // Handle case when reservasiOperasi with given id not found
            return null;
        }
    }

    // Delete operation
    public void deleteReservasiOperasi(Long id) {
        reservasiOperasiRepository.deleteById(id);
    }
}