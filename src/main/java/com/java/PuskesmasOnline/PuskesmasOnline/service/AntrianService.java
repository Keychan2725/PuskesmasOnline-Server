package com.java.PuskesmasOnline.PuskesmasOnline.service;


import com.java.PuskesmasOnline.PuskesmasOnline.model.Antrian;
import com.java.PuskesmasOnline.PuskesmasOnline.repository.AntrianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AntrianService {

    @Autowired
    private AntrianRepository antrianRepository;

    public List<Antrian> getAllAntrian() {
        return antrianRepository.findAll();
    }

    public Optional<Antrian> getAntrianById(Long id) {
        return antrianRepository.findById(id);
    }

    public Antrian saveOrUpdateAntrian(Antrian antrian) {
        return antrianRepository.save(antrian);
    }

    public void deleteAntrian(Long id) {
        antrianRepository.deleteById(id);
    }

}
