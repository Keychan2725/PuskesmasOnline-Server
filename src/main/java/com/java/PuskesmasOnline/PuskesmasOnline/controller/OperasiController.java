package com.java.PuskesmasOnline.PuskesmasOnline.controller;

import com.java.PuskesmasOnline.PuskesmasOnline.model.Operasi;
import com.java.PuskesmasOnline.PuskesmasOnline.service.OperasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class OperasiController {

    @Autowired
    private OperasiService operasiService;

    // Create
    @PostMapping("/admin/operasi/create")
    public ResponseEntity<Operasi> createOrUpdateOperasi(@RequestBody Operasi operasi) {
        Operasi createdOperasi = operasiService.createOrUpdate(operasi);
        return new ResponseEntity<>(createdOperasi, HttpStatus.CREATED);
    }

    // Read
    @GetMapping("/admin/operasi/all")
    public ResponseEntity<List<Operasi>> getAllOperasi() {
        List<Operasi> operasiList = operasiService.getAllOperasi();
        return new ResponseEntity<>(operasiList, HttpStatus.OK);
    }

    @GetMapping("/admin/operasi/{id}")
    public ResponseEntity<Operasi> getOperasiById(@PathVariable Long id) {
        Optional<Operasi> operasiOptional = operasiService.getOperasiById(id);
        return operasiOptional.map(operasi -> new ResponseEntity<>(operasi, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update
    @PutMapping("/admin/operasi/update/{id}")
    public ResponseEntity<Operasi> updateOperasi(@PathVariable Long id, @RequestBody Operasi newOperasi) {
        Operasi updatedOperasi = operasiService.updateOperasi(id, newOperasi);
        if (updatedOperasi != null) {
            return new ResponseEntity<>(updatedOperasi, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete
    @DeleteMapping("/admin/operasi/delete/{id}")
    public ResponseEntity<Void> deleteOperasi(@PathVariable Long id) {
        operasiService.deleteOperasi(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
