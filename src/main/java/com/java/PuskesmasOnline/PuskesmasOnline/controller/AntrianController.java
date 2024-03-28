package com.java.PuskesmasOnline.PuskesmasOnline.controller;
import com.java.PuskesmasOnline.PuskesmasOnline.model.Antrian;
import com.java.PuskesmasOnline.PuskesmasOnline.service.AntrianService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Antrian> getAllAntrian() {
        return antrianService.getAllAntrian();
    }

    @GetMapping("/antrian/{id}")
    public Optional<Antrian> getAntrianById(@PathVariable Long id) {
        return antrianService.getAntrianById(id);
    }

    @PostMapping("/antrian/add")
    public Antrian addAntrian(@RequestBody Antrian antrian) {
        return antrianService.saveOrUpdateAntrian(antrian);
    }

    @PutMapping("/antrian/update/{id}")
    public Antrian updateAntrian(@PathVariable Long id, @RequestBody Antrian updatedAntrian) {
        updatedAntrian.setId(id);
        return antrianService.saveOrUpdateAntrian(updatedAntrian);
    }

    @DeleteMapping("/antrian/delete/{id}")
    public void deleteAntrian(@PathVariable Long id) {
        antrianService.deleteAntrian(id);
    }
}
