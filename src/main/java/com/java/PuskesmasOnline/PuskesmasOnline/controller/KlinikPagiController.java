package com.java.PuskesmasOnline.PuskesmasOnline.controller;


 import com.java.PuskesmasOnline.PuskesmasOnline.model.KlinikPagi;
 import com.java.PuskesmasOnline.PuskesmasOnline.service.KlinikPagiService;
 import com.java.PuskesmasOnline.PuskesmasOnline.service.UserDetail;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
 import org.springframework.security.core.annotation.AuthenticationPrincipal;
 import org.springframework.security.core.userdetails.UserDetails;
 import org.springframework.web.bind.annotation.*;

 import javax.swing.*;
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
    @GetMapping("/admin/klinikpagi/{klinikId}")
    public ResponseEntity<List<KlinikPagi>> getKlinikPagiByKlinikId(@PathVariable String klinikId) {
        List<KlinikPagi> klinikPagiList = klinikPagiService.getByKlinikId(klinikId);
        if (!klinikPagiList.isEmpty()) {
            return new ResponseEntity<>(klinikPagiList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/admin/klinikpagi/get/{id}")
    public ResponseEntity<Optional<KlinikPagi>> getKlinikPagiById(@PathVariable Long id) {
        Optional<KlinikPagi> klinikPagiList = klinikPagiService.getKlinikById(id);
        if (!klinikPagiList.isEmpty()) {
            return new ResponseEntity<>(klinikPagiList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/admin/klinikpagi/update/{id}")
    public ResponseEntity<KlinikPagi> updateKlinikPagi(@PathVariable Long id, @RequestBody KlinikPagi newKlinikPagi) {
        KlinikPagi updatedKlinikPagi = klinikPagiService.updateKlinikPagi(id, newKlinikPagi);
        if (updatedKlinikPagi != null) {
            return new ResponseEntity<>(updatedKlinikPagi, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/admin/klinikpagi/ambilantrian/{id}")
    public ResponseEntity<KlinikPagi> ambilAntrian(@PathVariable Long id, @RequestParam String idUser) {
        Optional<KlinikPagi> klinikPagiOptional = klinikPagiService.ambilAntrian(id, idUser);
                if (klinikPagiOptional.isPresent()) {
                    return ResponseEntity.ok(klinikPagiOptional.get());
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
    }


    @DeleteMapping("/admin/klinikpagi/delete/{id}")
    public ResponseEntity<Void> deleteKlinikPagi(@PathVariable Long id) {
        klinikPagiService.deleteKlinikPagi(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
