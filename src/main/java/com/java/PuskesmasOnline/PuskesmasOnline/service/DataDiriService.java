package com.java.PuskesmasOnline.PuskesmasOnline.service;


import com.java.PuskesmasOnline.PuskesmasOnline.model.DataDiri;
import com.java.PuskesmasOnline.PuskesmasOnline.repository.DataDiriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataDiriService {

    @Autowired
    private DataDiriRepository dataDiriRepository;


    // Create operation
    public DataDiri createOrUpdate(DataDiri dataDiri) {
        return dataDiriRepository.save(dataDiri);
    }

    // Read operation
    public List<DataDiri> getAllDataDiri() {
        return dataDiriRepository.findAll();
    }

    public Optional<DataDiri> getDataDiriById(Long id) {
        return dataDiriRepository.findById(id);
    }

    // Update operation
    public DataDiri updateDataDiri(Long id, DataDiri newDataDiri) {
        Optional<DataDiri> existingDataDiriOptional = dataDiriRepository.findById(id);
        if (existingDataDiriOptional.isPresent()) {
            DataDiri existingDataDiri = existingDataDiriOptional.get();
            existingDataDiri.setNamaDepan(newDataDiri.getNamaDepan());
            existingDataDiri.setNamaBelakang(newDataDiri.getNamaBelakang());
            existingDataDiri.setUsia(newDataDiri.getUsia());
            existingDataDiri.setIdUser(newDataDiri.getIdUser());
            existingDataDiri.setGender(newDataDiri.getGender());
            existingDataDiri.setNik(newDataDiri.getNik());
            return dataDiriRepository.save(existingDataDiri);
        } else {
            // Handle case when dataDiri with given id not found
            return null;
        }
    }

    // Delete operation
    public void deleteDataDiri(Long id) {
        dataDiriRepository.deleteById(id);
    }

}
