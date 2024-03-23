package com.java.PuskesmasOnline.PuskesmasOnline.controller;

import com.java.PuskesmasOnline.PuskesmasOnline.model.BuktiPembayaran;
import com.java.PuskesmasOnline.PuskesmasOnline.service.BuktiPembayaranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class BuktiPembayaranController {

    @Autowired
    private BuktiPembayaranService buktiPembayaranService;

    // Create
    @PostMapping("/buktipembayaran/create")
    public ResponseEntity<BuktiPembayaran> createOrUpdateBuktiPembayaran(@RequestBody BuktiPembayaran buktiPembayaran) {
        BuktiPembayaran createdBuktiPembayaran = buktiPembayaranService.createOrUpdate(buktiPembayaran);
        return new ResponseEntity<>(createdBuktiPembayaran, HttpStatus.CREATED);
    }

    // Read
    @GetMapping("/buktipembayaran/all")
    public ResponseEntity<List<BuktiPembayaran>> getAllBuktiPembayaran() {
        List<BuktiPembayaran> buktiPembayaranList = buktiPembayaranService.getAllBuktiPembayaran();
        return new ResponseEntity<>(buktiPembayaranList, HttpStatus.OK);
    }

    @GetMapping("/buktipembayaran/{id}")
    public ResponseEntity<BuktiPembayaran> getBuktiPembayaranById(@PathVariable Long id) {
        Optional<BuktiPembayaran> buktiPembayaranOptional = buktiPembayaranService.getBuktiPembayaranById(id);
        return buktiPembayaranOptional.map(buktiPembayaran -> new ResponseEntity<>(buktiPembayaran, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update
    @PutMapping("/buktipembayaran/update/{id}")
    public ResponseEntity<BuktiPembayaran> updateBuktiPembayaran(@PathVariable Long id, @RequestBody BuktiPembayaran newBuktiPembayaran) {
        BuktiPembayaran updatedBuktiPembayaran = buktiPembayaranService.updateBuktiPembayaran(id, newBuktiPembayaran);
        if (updatedBuktiPembayaran != null) {
            return new ResponseEntity<>(updatedBuktiPembayaran, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete
    @DeleteMapping("/buktipembayaran/delete/{id}")
    public ResponseEntity<Void> deleteBuktiPembayaran(@PathVariable Long id) {
        buktiPembayaranService.deleteBuktiPembayaran(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
