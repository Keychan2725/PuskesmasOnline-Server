package com.java.PuskesmasOnline.PuskesmasOnline.service;

import com.java.PuskesmasOnline.PuskesmasOnline.model.BuktiPembayaran;
import com.java.PuskesmasOnline.PuskesmasOnline.repository.BuktiPembayaranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuktiPembayaranService {

    @Autowired
    private BuktiPembayaranRepository buktiPembayaranRepository;

    // Create operation
    public BuktiPembayaran createOrUpdate(BuktiPembayaran buktiPembayaran) {
        return buktiPembayaranRepository.save(buktiPembayaran);
    }

    // Read operation
    public List<BuktiPembayaran> getAllBuktiPembayaran() {
        return buktiPembayaranRepository.findAll();
    }

    public Optional<BuktiPembayaran> getBuktiPembayaranById(Long id) {
        return buktiPembayaranRepository.findById(id);
    }

    // Update operation
    public BuktiPembayaran updateBuktiPembayaran(Long id, BuktiPembayaran newBuktiPembayaran) {
        Optional<BuktiPembayaran> existingBuktiPembayaranOptional = buktiPembayaranRepository.findById(id);
        if (existingBuktiPembayaranOptional.isPresent()) {
            BuktiPembayaran existingBuktiPembayaran = existingBuktiPembayaranOptional.get();
            existingBuktiPembayaran.setIdPasien(newBuktiPembayaran.getIdPasien());
            existingBuktiPembayaran.setIdKlinik(newBuktiPembayaran.getIdKlinik());
            existingBuktiPembayaran.setImg(newBuktiPembayaran.getImg());
            existingBuktiPembayaran.setStatus(newBuktiPembayaran.getStatus());
            existingBuktiPembayaran.setTanggalWaktu(newBuktiPembayaran.getTanggalWaktu());
            return buktiPembayaranRepository.save(existingBuktiPembayaran);
        } else {
            // Handle case when buktiPembayaran with given id not found
            return null;
        }
    }

    // Delete operation
    public void deleteBuktiPembayaran(Long id) {
        buktiPembayaranRepository.deleteById(id);
    }
}
