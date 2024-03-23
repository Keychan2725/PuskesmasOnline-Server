package com.java.PuskesmasOnline.PuskesmasOnline.controller;

import com.java.PuskesmasOnline.PuskesmasOnline.model.Ruangan;
import com.java.PuskesmasOnline.PuskesmasOnline.service.RuanganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class RuanganController {

    @Autowired
    private RuanganService ruanganService;

    // Create
    @PostMapping("/admin/ruangan/create")
    public ResponseEntity<Ruangan> createOrUpdateRuangan(@RequestBody Ruangan ruangan) {
        Ruangan createdRuangan = ruanganService.createOrUpdate(ruangan);
        return new ResponseEntity<>(createdRuangan, HttpStatus.CREATED);
    }

    // Read
    @GetMapping("/admin/ruangan/all")
    public ResponseEntity<List<Ruangan>> getAllRuangan() {
        List<Ruangan> ruanganList = ruanganService.getAllRuangan();
        return new ResponseEntity<>(ruanganList, HttpStatus.OK);
    }

    @GetMapping("/admin/ruangan/{id}")
    public ResponseEntity<Ruangan> getRuanganById(@PathVariable Long id) {
        Optional<Ruangan> ruanganOptional = ruanganService.getRuanganById(id);
        return ruanganOptional.map(ruangan -> new ResponseEntity<>(ruangan, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update
    @PutMapping("/admin/ruangan/update/{id}")
    public ResponseEntity<Ruangan> updateRuangan(@PathVariable Long id, @RequestBody Ruangan newRuangan) {
        Ruangan updatedRuangan = ruanganService.updateRuangan(id, newRuangan);
        if (updatedRuangan != null) {
            return new ResponseEntity<>(updatedRuangan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete
    @DeleteMapping("/admin/ruangan/delete/{id}")
    public ResponseEntity<Void> deleteRuangan(@PathVariable Long id) {
        ruanganService.deleteRuangan(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}