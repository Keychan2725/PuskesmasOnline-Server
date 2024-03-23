package com.java.PuskesmasOnline.PuskesmasOnline.service;

import com.java.PuskesmasOnline.PuskesmasOnline.model.Operasi;
import com.java.PuskesmasOnline.PuskesmasOnline.repository.OperasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperasiService {

    @Autowired
    private OperasiRepository operasiRepository;

    // Create operation
    public Operasi createOrUpdate(Operasi operasi) {
        return operasiRepository.save(operasi);
    }

    // Read operation
    public List<Operasi> getAllOperasi() {
        return operasiRepository.findAll();
    }

    public Optional<Operasi> getOperasiById(Long id) {
        return operasiRepository.findById(id);
    }

    // Update operation
    public Operasi updateOperasi(Long id, Operasi newOperasi) {
        Optional<Operasi> existingOperasiOptional = operasiRepository.findById(id);
        if (existingOperasiOptional.isPresent()) {
            Operasi existingOperasi = existingOperasiOptional.get();
            existingOperasi.setNamaPasien(newOperasi.getNamaPasien());
            existingOperasi.setNameOperasi(newOperasi.getNameOperasi());
            existingOperasi.setIdPasien(newOperasi.getIdPasien());
            existingOperasi.setIdKlinik(newOperasi.getIdKlinik());
            existingOperasi.setIdRuangan(newOperasi.getIdRuangan());
            existingOperasi.setStatus(newOperasi.getStatus());
            existingOperasi.setTanggalWaktu(newOperasi.getTanggalWaktu());
            return operasiRepository.save(existingOperasi);
        } else {
            // Handle case when operasi with given id not found
            return null;
        }
    }

    // Delete operation
    public void deleteOperasi(Long id) {
        operasiRepository.deleteById(id);
    }
}
