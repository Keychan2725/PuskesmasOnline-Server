package com.java.PuskesmasOnline.PuskesmasOnline.controller;


import com.java.PuskesmasOnline.PuskesmasOnline.model.KlinikPagi;
import com.java.PuskesmasOnline.PuskesmasOnline.service.KlinikPagiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class KlinikPagiController {
    @Autowired
    private KlinikPagiService klinikPagiService;

    // Create
    @PostMapping("/admin/klinikpagi/create")
    public ResponseEntity<KlinikPagi> createOrUpdateKlinikPagi(@RequestBody KlinikPagi klinikPagi) {
        KlinikPagi createdKlinikPagi = klinikPagiService.createOrUpdate(klinikPagi);
        return new ResponseEntity<>(createdKlinikPagi, HttpStatus.CREATED);
    }

    // Read
    @GetMapping("/admin/klinikpagi/all")
    public ResponseEntity<List<KlinikPagi>> getAllKlinikPagi() {
        List<KlinikPagi> klinikPagiList = klinikPagiService.getAllKlinikPagi();
        return new ResponseEntity<>(klinikPagiList, HttpStatus.OK);
    }

    @GetMapping("/admin/klinikpagi/{id}")
    public ResponseEntity<KlinikPagi> getKlinikPagiById(@PathVariable Long id) {
        Optional<KlinikPagi> klinikPagiOptional = klinikPagiService.getKlinikPagiById(id);
        return klinikPagiOptional.map(klinikPagi -> new ResponseEntity<>(klinikPagi, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update
    @PutMapping("/admin/klinikpagi/update/{id}")
    public ResponseEntity<KlinikPagi> updateKlinikPagi(@PathVariable Long id, @RequestBody KlinikPagi newKlinikPagi) {
        KlinikPagi updatedKlinikPagi = klinikPagiService.updateKlinikPagi(id, newKlinikPagi);
        if (updatedKlinikPagi != null) {
            return new ResponseEntity<>(updatedKlinikPagi, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete
    @DeleteMapping("/admin/klinikpagi/delete/{id}")
    public ResponseEntity<Void> deleteKlinikPagi(@PathVariable Long id) {
        klinikPagiService.deleteKlinikPagi(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
