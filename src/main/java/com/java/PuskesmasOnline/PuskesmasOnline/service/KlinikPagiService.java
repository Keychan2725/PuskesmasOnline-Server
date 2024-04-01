package com.java.PuskesmasOnline.PuskesmasOnline.service;


import com.java.PuskesmasOnline.PuskesmasOnline.model.KlinikPagi;
import com.java.PuskesmasOnline.PuskesmasOnline.model.User;
import com.java.PuskesmasOnline.PuskesmasOnline.repository.KlinikPagiRepository;
import com.java.PuskesmasOnline.PuskesmasOnline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class KlinikPagiService  {

    @Autowired
    private UserRepository userRepository;

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


    public Optional<KlinikPagi> ambilAntrian(String klinikId) {
        // Cari klinik dengan ID yang sesuai
        Optional<KlinikPagi> klinikPagiOptional = klinikPagiRepository.findByKlinikId(klinikId);

        // Jika klinik ditemukan
        if (klinikPagiOptional.isPresent()) {
            KlinikPagi klinikPagi = klinikPagiOptional.get();

            // Ambil nomor antrian dan kurangi 1
            int nomorAntrianSebelumnya = Integer.parseInt(klinikPagi.getNoAntrian()) - 1;

            // Update nomor antrian
            klinikPagi.setNoAntrian(String.valueOf(nomorAntrianSebelumnya));

            // Simpan perubahan ke dalam database
            klinikPagiRepository.save(klinikPagi);

            return Optional.of(klinikPagi);
        } else {
            return Optional.empty(); // Klinik tidak ditemukan
        }
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
