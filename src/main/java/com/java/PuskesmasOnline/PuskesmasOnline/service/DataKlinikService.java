package com.java.PuskesmasOnline.PuskesmasOnline.service;

import com.java.PuskesmasOnline.PuskesmasOnline.exception.NotFoundException;
import com.java.PuskesmasOnline.PuskesmasOnline.model.DataKlinik;
import com.java.PuskesmasOnline.PuskesmasOnline.repository.DataKlinikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataKlinikService {

    @Autowired
    private DataKlinikRepository dataKlinikRepository;

    // Create operation
    public DataKlinik createOrUpdate(DataKlinik dataKlinik) {
        return dataKlinikRepository.save(dataKlinik);
    }

    // Read operation
    public List<DataKlinik> getAllDataKlinik() {
        return dataKlinikRepository.findAll();
    }

    public Optional<DataKlinik> getDataKlinikById(Long id) {
        return dataKlinikRepository.findById(id);
    }

    // Update operation
    public DataKlinik updateDataKlinik(Long id, DataKlinik newDataKlinik) {
        Optional<DataKlinik> existingDataKlinikOptional = dataKlinikRepository.findById(id);
        if (existingDataKlinikOptional.isPresent()) {
            DataKlinik existingDataKlinik = existingDataKlinikOptional.get();
            existingDataKlinik.setNamaklinik(newDataKlinik.getNamaklinik());
            existingDataKlinik.setAlamatKlinik(newDataKlinik.getAlamatKlinik());
            existingDataKlinik.setPemilik(newDataKlinik.getPemilik());
            existingDataKlinik.setIdUser(newDataKlinik.getIdUser());
            existingDataKlinik.setOperasiClient(newDataKlinik.getOperasiClient());
            return dataKlinikRepository.save(existingDataKlinik);
        } else {
            // Handle case when dataKlinik with given id not found
            throw  new NotFoundException("Gagal Melakukan Update");
        }
    }

    // Delete operation
    public void deleteDataKlinik(Long id) {
        dataKlinikRepository.deleteById(id);
    }

}
