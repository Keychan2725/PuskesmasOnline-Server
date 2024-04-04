package com.java.PuskesmasOnline.PuskesmasOnline.service;


import com.java.PuskesmasOnline.PuskesmasOnline.model.Antrian;
import com.java.PuskesmasOnline.PuskesmasOnline.model.KlinikPagi;
import com.java.PuskesmasOnline.PuskesmasOnline.repository.AntrianRepository;
import com.java.PuskesmasOnline.PuskesmasOnline.repository.KlinikPagiRepository;
import com.java.PuskesmasOnline.PuskesmasOnline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

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
        klinikPagi.setCount(klinikPagi.getNoAntrian());
        return klinikPagiRepository.save(klinikPagi);
    }

    public List<KlinikPagi> getAllKlinikPagi() {
        return klinikPagiRepository.findAll();
    }


    public Optional<KlinikPagi> getKlinikById(Long id) {
        return klinikPagiRepository.findById(id);
    }


    private HashMap<String, LocalDateTime> userRequestTimestamps = new HashMap<String, LocalDateTime>();

    public Optional<KlinikPagi> ambilAntrian(Long id, String idUser) {
        Optional<KlinikPagi> klinikPagiOptional = klinikPagiRepository.findById(id);

        if (klinikPagiOptional.isPresent()) {
            KlinikPagi klinikPagi = klinikPagiOptional.get();

            if (canTakeTicket(idUser)) {
                processTicketRequest(klinikPagi, idUser);
                return klinikPagiOptional;
            }
        }

        return Optional.empty();
    }

//    public List<Object> findAllByIdklinik(String klinikId) {
//        Optional<KlinikPagi> klinikPagiOptional = klinikPagiRepository.findByKlinikId(klinikId);
//        if (klinikPagiOptional.isPresent()) {
//            KlinikPagi klinikPagi = klinikPagiOptional.get();
//            return Collections.singletonList(klinikPagiRepository.findByKlinikId(klinikId));
//        } else {
//            return Collections.emptyList();
//        }
//    }
    private void processTicketRequest(KlinikPagi klinikPagi, String idUser) {
        int nomorAntrianSebelumnya = Integer.parseInt(klinikPagi.getNoAntrian()) - 1;
        klinikPagi.setNoAntrian(String.valueOf(nomorAntrianSebelumnya));
        klinikPagiRepository.save(klinikPagi);

        Antrian antrian = new Antrian();
        LocalDateTime currentDate = LocalDateTime.now();
        antrian.setIdKlinik(klinikPagi.getKlinikId());
        antrian.setNamaKlinik(klinikPagi.getNamaKlinik());
        antrian.setIdUser(idUser);
        antrian.setTanggalWaktu(Date.from(currentDate.atZone(ZoneOffset.systemDefault()).toInstant()));
        antrian.setStatus("Sukses");
        antrian.setNoAntrian(String.valueOf(nomorAntrianSebelumnya));

        antrianRepository.save(antrian);
    }

    private boolean canTakeTicket(String idUser) {
        LocalDateTime tenHoursAgo = LocalDateTime.now().minusHours(10);
        LocalDateTime lastRequestTime = userRequestTimestamps.get(idUser);

        if (lastRequestTime == null) {
            return true;
        }

        if (lastRequestTime.isBefore(tenHoursAgo)) {
            putIfAbsent(idUser, LocalDateTime.now());
            return true;
        }

        return false;
    }

    private void putIfAbsent(String idUser, LocalDateTime timestamp) {
        userRequestTimestamps.putIfAbsent(idUser, timestamp);
    }
    public List<KlinikPagi> getByKlinikId (String klinikId) {
        return klinikPagiRepository.getAllByKlinikId(klinikId);
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

            return null;
        }
    }

    // Delete operation
    public void deleteKlinikPagi(Long id) {
        klinikPagiRepository.deleteById(id);
    }



}
