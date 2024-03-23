package com.java.PuskesmasOnline.PuskesmasOnline.service;

import com.java.PuskesmasOnline.PuskesmasOnline.model.Ruangan;
import com.java.PuskesmasOnline.PuskesmasOnline.repository.RuanganRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuanganService {

    @Autowired
    private RuanganRepository ruanganRepository;

    // Create operation
    public Ruangan createOrUpdate(Ruangan ruangan) {
        return ruanganRepository.save(ruangan);
    }

    // Read operation
    public List<Ruangan> getAllRuangan() {
        return ruanganRepository.findAll();
    }

    public Optional<Ruangan> getRuanganById(Long id) {
        return ruanganRepository.findById(id);
    }

    // Update operation
    public Ruangan updateRuangan(Long id, Ruangan newRuangan) {
        Optional<Ruangan> existingRuanganOptional = ruanganRepository.findById(id);
        if (existingRuanganOptional.isPresent()) {
            Ruangan existingRuangan = existingRuanganOptional.get();
            existingRuangan.setNamaRuangan(newRuangan.getNamaRuangan());
            existingRuangan.setNamaPasien(newRuangan.getNamaPasien());
            existingRuangan.setPembayaran(newRuangan.getPembayaran());
            existingRuangan.setIdOperasi(newRuangan.getIdOperasi());
            existingRuangan.setStatus(newRuangan.getStatus());
            existingRuangan.setTanggalWaktu(newRuangan.getTanggalWaktu());
            return ruanganRepository.save(existingRuangan);
        } else {
            // Handle case when ruangan with given id not found
            return null;
        }
    }

    // Delete operation
    public void deleteRuangan(Long id) {
        ruanganRepository.deleteById(id);
    }
}