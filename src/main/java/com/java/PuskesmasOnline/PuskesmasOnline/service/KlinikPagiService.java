package com.java.PuskesmasOnline.PuskesmasOnline.service;


import com.java.PuskesmasOnline.PuskesmasOnline.model.Antrian;
import com.java.PuskesmasOnline.PuskesmasOnline.model.KlinikPagi;
import com.java.PuskesmasOnline.PuskesmasOnline.repository.AntrianRepository;
import com.java.PuskesmasOnline.PuskesmasOnline.repository.KlinikPagiRepository;
import com.java.PuskesmasOnline.PuskesmasOnline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class KlinikPagiService  {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AntrianRepository antrianRepository;

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


    public Optional<KlinikPagi> getKlinikById(Long id) {
        return klinikPagiRepository.findById(id);
    }



    private HashMap<String, Date> userRequestTimestamps = new HashMap<>();

    public Optional<KlinikPagi> ambilAntrian(Long id, String idUser) {
//        if (userRequestTimestamps.containsKey(idUser) &&
//                (new Date().getTime() - userRequestTimestamps.get(idUser).getTime()) < (10 * 60 * 60 * 1000)) {
//            return Optional.empty();
//        }

        Optional<KlinikPagi> klinikPagiOptional = klinikPagiRepository.findById(id);

        if (klinikPagiOptional.isPresent()) {
            KlinikPagi klinikPagi = klinikPagiOptional.get();

            int nomorAntrianSebelumnya = Integer.parseInt(klinikPagi.getNoAntrian()) - 1;
            klinikPagi.setNoAntrian(String.valueOf(nomorAntrianSebelumnya));
            klinikPagiRepository.save(klinikPagi);

            Antrian antrian = new Antrian();
            Date currentDate = new Date();
            antrian.setIdKlinik(klinikPagi.getKlinikId());
            antrian.setIdKlinikPagi(String.valueOf(klinikPagi.getId()));
            antrian.setIdUser(idUser);
            antrian.setTanggalWaktu(currentDate);
            antrian.setStatus("Sukses");
            antrian.setNoAntrian(String.valueOf(nomorAntrianSebelumnya));

            antrianRepository.save(antrian);

            userRequestTimestamps.put(idUser, new Date());

            return klinikPagiOptional;
        } else {
            return Optional.empty();
        }
    }

    public Optional<KlinikPagi> getByKlinikId (String klinikId) {
        return klinikPagiRepository.findByKlinikId(klinikId);
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
