package com.java.PuskesmasOnline.PuskesmasOnline.controller;

import com.google.api.Http;
import com.java.PuskesmasOnline.PuskesmasOnline.model.DataDiri;
import com.java.PuskesmasOnline.PuskesmasOnline.service.DataDiriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class DataDiriController {

    @Autowired
    private DataDiriService dataDiriService;

    // Create
    @PostMapping("/user/klinik/dataDiri/create")
    public ResponseEntity<DataDiri> createOrUpdateDataDiri(@RequestBody DataDiri dataDiri) {
        DataDiri createdDataDiri = dataDiriService.createOrUpdate(dataDiri);
        return new ResponseEntity<>(createdDataDiri, HttpStatus.CREATED);
    }

    // Read
    @GetMapping("/user/klinik/dataDiri/all")
    public ResponseEntity<List<DataDiri>> getAllDataDiri() {
        List<DataDiri> dataDiriList = dataDiriService.getAllDataDiri();
        return new ResponseEntity<>(dataDiriList, HttpStatus.OK);
    }

    @GetMapping("/user/klinik/dataDiri/{id}")
    public ResponseEntity<DataDiri> getDataDiriById(@PathVariable Long id) {
        Optional<DataDiri> dataDiriOptional = dataDiriService.getDataDiriById(id);
        return dataDiriOptional.map(dataDiri -> new ResponseEntity<>(dataDiri, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update
    @PutMapping("/user/klinik/dataDiri/update/{id}")
    public ResponseEntity<DataDiri> updateDataDiri(@PathVariable Long id, @RequestBody DataDiri newDataDiri) {
        DataDiri updatedDataDiri = dataDiriService.updateDataDiri(id, newDataDiri);
        if (updatedDataDiri != null) {
            return new ResponseEntity<>(updatedDataDiri, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/klinik/getDataDiriByIdUser/{idUser}")
    public ResponseEntity<DataDiri> getDataByIdUser (@PathVariable String idUser ){
        Optional<DataDiri> dataDiriList = dataDiriService.getDataByIdUser(idUser);
        return dataDiriList.map(dataDiri -> new ResponseEntity<>(dataDiri, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Delete
    @DeleteMapping("/user/klinik/dataDiri/delete/{id}")
    public ResponseEntity<Void> deleteDataDiri(@PathVariable Long id) {
        dataDiriService.deleteDataDiri(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
