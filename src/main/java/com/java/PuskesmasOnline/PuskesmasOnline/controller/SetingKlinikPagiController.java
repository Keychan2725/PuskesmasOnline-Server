package com.java.PuskesmasOnline.PuskesmasOnline.controller;
import com.java.PuskesmasOnline.PuskesmasOnline.model.SetingKlinikPagi;
import com.java.PuskesmasOnline.PuskesmasOnline.service.SetingKlinikPagiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class SetingKlinikPagiController {

    @Autowired
    private SetingKlinikPagiService setingKlinikPagiService;

    // Create
    @PostMapping("/setingklinikpagi/create")
    public ResponseEntity<SetingKlinikPagi> createOrUpdateSetingKlinikPagi(@RequestBody SetingKlinikPagi setingKlinikPagi) {
        SetingKlinikPagi createdSetingKlinikPagi = setingKlinikPagiService.createOrUpdate(setingKlinikPagi);
        return new ResponseEntity<>(createdSetingKlinikPagi, HttpStatus.CREATED);
    }

    // Read
    @GetMapping("/setingklinikpagi/all")
    public ResponseEntity<List<SetingKlinikPagi>> getAllSetingKlinikPagi() {
        List<SetingKlinikPagi> setingKlinikPagiList = setingKlinikPagiService.getAllSetingKlinikPagi();
        return new ResponseEntity<>(setingKlinikPagiList, HttpStatus.OK);
    }

    @GetMapping("/setingklinikpagi/{id}")
    public ResponseEntity<SetingKlinikPagi> getSetingKlinikPagiById(@PathVariable Long id) {
        Optional<SetingKlinikPagi> setingKlinikPagiOptional = setingKlinikPagiService.getSetingKlinikPagiById(id);
        return setingKlinikPagiOptional.map(setingKlinikPagi -> new ResponseEntity<>(setingKlinikPagi, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update
    @PutMapping("/setingklinikpagi/update/{id}")
    public ResponseEntity<SetingKlinikPagi> updateSetingKlinikPagi(@PathVariable Long id, @RequestBody SetingKlinikPagi newSetingKlinikPagi) {
        SetingKlinikPagi updatedSetingKlinikPagi = setingKlinikPagiService.updateSetingKlinikPagi(id, newSetingKlinikPagi);
        if (updatedSetingKlinikPagi != null) {
            return new ResponseEntity<>(updatedSetingKlinikPagi, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete
    @DeleteMapping("/setingklinikpagi/delete/{id}")
    public ResponseEntity<Void> deleteSetingKlinikPagi(@PathVariable Long id) {
        setingKlinikPagiService.deleteSetingKlinikPagi(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
