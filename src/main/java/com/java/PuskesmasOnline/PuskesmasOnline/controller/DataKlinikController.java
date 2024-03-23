package com.java.PuskesmasOnline.PuskesmasOnline.controller;

import com.java.PuskesmasOnline.PuskesmasOnline.model.DataKlinik;
import com.java.PuskesmasOnline.PuskesmasOnline.service.DataKlinikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class DataKlinikController {

    @Autowired
    private DataKlinikService dataKlinikService;

    // Create
    @PostMapping("/admin/create")
    public ResponseEntity<DataKlinik> createOrUpdateDataKlinik(@RequestBody DataKlinik dataKlinik) {
        DataKlinik createdDataKlinik = dataKlinikService.createOrUpdate(dataKlinik);
        return new ResponseEntity<>(createdDataKlinik, HttpStatus.CREATED);
    }

    // Read
    @GetMapping("/admin/all")
    public ResponseEntity<List<DataKlinik>> getAllDataKlinik() {
        List<DataKlinik> dataKlinikList = dataKlinikService.getAllDataKlinik();
        return new ResponseEntity<>(dataKlinikList, HttpStatus.OK);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<DataKlinik> getDataKlinikById(@PathVariable Long id) {
        Optional<DataKlinik> dataKlinikOptional = dataKlinikService.getDataKlinikById(id);
        return dataKlinikOptional.map(dataKlinik -> new ResponseEntity<>(dataKlinik, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update
    @PutMapping("/admin/update/{id}")
    public ResponseEntity<DataKlinik> updateDataKlinik(@PathVariable Long id, @RequestBody DataKlinik newDataKlinik) {
        DataKlinik updatedDataKlinik = dataKlinikService.updateDataKlinik(id, newDataKlinik);
        if (updatedDataKlinik != null) {
            return new ResponseEntity<>(updatedDataKlinik, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete
    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<Void> deleteDataKlinik(@PathVariable Long id) {
        dataKlinikService.deleteDataKlinik(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}