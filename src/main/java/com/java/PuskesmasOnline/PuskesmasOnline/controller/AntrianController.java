package com.java.PuskesmasOnline.PuskesmasOnline.controller;
import com.java.PuskesmasOnline.PuskesmasOnline.model.Antrian;
import com.java.PuskesmasOnline.PuskesmasOnline.service.AntrianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class AntrianController {

    @Autowired
    private AntrianService antrianService;

    @GetMapping("/antrian/all")
    public ResponseEntity<List<Antrian>> getAllAntrian() {
        List<Antrian> antrians = antrianService.getAllAntrian();
        return new ResponseEntity<>(antrians, HttpStatus.OK);
    }

    @GetMapping("/antrian/{id}")
    public ResponseEntity<Antrian> getAntrianById(@PathVariable Long id) {
        Optional<Antrian> antrian = antrianService.getAntrianById(id);
        return antrian.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/antrian/{idUser}")
    public ResponseEntity<Antrian> getAntrianByIdUser(@PathVariable String idUser) {
        Optional<Antrian> antrian = antrianService.getAntrianByUserId(idUser);
        return antrian.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/antrian/all/{idUser}")
    public ResponseEntity<List<Antrian>> getAllAntrianByIdUser (@PathVariable String idUser) {
        List<Antrian> findall = antrianService.getAllAntrianByIdUser(idUser);

        if (!findall.isEmpty()) {
            return new ResponseEntity<>(findall, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/antrian/editantrian")
    public ResponseEntity<Antrian> createNotaAntrian(@RequestBody Antrian antrian) {

        Optional<Antrian> createdAntrian = Optional.ofNullable(antrianService.saveOrUpdateAntrian(antrian));

        if (createdAntrian.isPresent()) {
            return new ResponseEntity<>(createdAntrian.get(), HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/antrian/update/{id}")
    public ResponseEntity<Antrian> updateAntrian(@PathVariable Long id, @RequestBody Antrian updatedAntrian) {
        updatedAntrian.setId(id);
        Antrian updated = antrianService.saveOrUpdateAntrian(updatedAntrian);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/antrian/delete/{id}")
    public ResponseEntity<Void> deleteAntrian(@PathVariable Long id) {
        antrianService.deleteAntrian(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
