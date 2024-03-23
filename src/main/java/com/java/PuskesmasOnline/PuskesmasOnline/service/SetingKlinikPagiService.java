package com.java.PuskesmasOnline.PuskesmasOnline.service;
import com.java.PuskesmasOnline.PuskesmasOnline.model.SetingKlinikPagi;
import com.java.PuskesmasOnline.PuskesmasOnline.repository.SetingKlinikPagiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SetingKlinikPagiService {

    @Autowired
    private SetingKlinikPagiRepository setingKlinikPagiRepository;

    // Create operation
    public SetingKlinikPagi createOrUpdate(SetingKlinikPagi setingKlinikPagi) {
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
            return setingKlinikPagiRepository.save(existingSetingKlinikPagi);
        } else {
            // Handle case when setingKlinikPagi with given id not found
            return null;
        }
    }

    // Delete operation
    public void deleteSetingKlinikPagi(Long id) {
        setingKlinikPagiRepository.deleteById(id);
    }
}
