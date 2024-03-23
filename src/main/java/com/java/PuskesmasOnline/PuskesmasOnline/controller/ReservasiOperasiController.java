package com.java.PuskesmasOnline.PuskesmasOnline.controller;

import com.java.PuskesmasOnline.PuskesmasOnline.model.ReservasiOperasi;
import com.java.PuskesmasOnline.PuskesmasOnline.service.ReservasiOperasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ReservasiOperasiController {

    @Autowired
    private ReservasiOperasiService reservasiOperasiService;

    // Create
    @PostMapping("/user/reservasi/create")
    public ResponseEntity<ReservasiOperasi> createOrUpdateReservasiOperasi(@RequestBody ReservasiOperasi reservasiOperasi) {
        ReservasiOperasi createdReservasiOperasi = reservasiOperasiService.createOrUpdate(reservasiOperasi);
        return new ResponseEntity<>(createdReservasiOperasi, HttpStatus.CREATED);
    }

    // Read
    @GetMapping("/user/reservasi/all")
    public ResponseEntity<List<ReservasiOperasi>> getAllReservasiOperasi() {
        List<ReservasiOperasi> reservasiOperasiList = reservasiOperasiService.getAllReservasiOperasi();
        return new ResponseEntity<>(reservasiOperasiList, HttpStatus.OK);
    }

    @GetMapping("/user/reservasi/{id}")
    public ResponseEntity<ReservasiOperasi> getReservasiOperasiById(@PathVariable Long id) {
        Optional<ReservasiOperasi> reservasiOperasiOptional = reservasiOperasiService.getReservasiOperasiById(id);
        return reservasiOperasiOptional.map(reservasiOperasi -> new ResponseEntity<>(reservasiOperasi, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update
    @PutMapping("/user/reservasi/update/{id}")
    public ResponseEntity<ReservasiOperasi> updateReservasiOperasi(@PathVariable Long id, @RequestBody ReservasiOperasi newReservasiOperasi) {
        ReservasiOperasi updatedReservasiOperasi = reservasiOperasiService.updateReservasiOperasi(id, newReservasiOperasi);
        if (updatedReservasiOperasi != null) {
            return new ResponseEntity<>(updatedReservasiOperasi, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete
    @DeleteMapping("/user/reservasi/delete/{id}")
    public ResponseEntity<Void> deleteReservasiOperasi(@PathVariable Long id) {
        reservasiOperasiService.deleteReservasiOperasi(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}