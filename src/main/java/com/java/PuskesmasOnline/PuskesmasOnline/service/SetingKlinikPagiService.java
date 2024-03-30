package com.java.PuskesmasOnline.PuskesmasOnline.service;
import com.java.PuskesmasOnline.PuskesmasOnline.model.SetingKlinikPagi;
import com.java.PuskesmasOnline.PuskesmasOnline.repository.SetingKlinikPagiRepository;
import com.java.PuskesmasOnline.PuskesmasOnline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SetingKlinikPagiService  {

    @Autowired
    private SetingKlinikPagiRepository setingKlinikPagiRepository;

    @Autowired
    private UserRepository userRepository;

    public SetingKlinikPagi createOrUpdate(SetingKlinikPagi setingKlinikPagi) {
        Date currentDate = new Date(); // Mendapatkan waktu saat ini
        setingKlinikPagi.setTanggalWaktu(currentDate);

        return setingKlinikPagiRepository.save(setingKlinikPagi);
    }

    // Read operation
    public List<SetingKlinikPagi> getAllSetingKlinikPagi() {
        return setingKlinikPagiRepository.findAll();
    }

    public Optional<SetingKlinikPagi> getSetingKlinikPagiById(Long id) {
        return setingKlinikPagiRepository.findById(id);
    }

    // Update operation
    public SetingKlinikPagi updateSetingKlinikPagi(Long id, SetingKlinikPagi newSetingKlinikPagi) {
        Optional<SetingKlinikPagi> existingSetingKlinikPagiOptional = setingKlinikPagiRepository.findById(id);
        if (existingSetingKlinikPagiOptional.isPresent()) {
            SetingKlinikPagi existingSetingKlinikPagi = existingSetingKlinikPagiOptional.get();
            existingSetingKlinikPagi.setJumlahNoAntrian(newSetingKlinikPagi.getJumlahNoAntrian());
            existingSetingKlinikPagi.setIdKlinik(newSetingKlinikPagi.getIdKlinik());
            existingSetingKlinikPagi.setTanggalWaktu(newSetingKlinikPagi.getTanggalWaktu());
            return setingKlinikPagiRepository.save(existingSetingKlinikPagi);
        } else {

            return null;
        }
    }

    // Delete operation
    public void deleteSetingKlinikPagi(Long id) {
        setingKlinikPagiRepository.deleteById(id);
    }
}
