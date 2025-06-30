package com.codegym.controller;

import com.codegym.model.Smartphone;
import com.codegym.service.SmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("api/smartphones")
public class SmartphoneController {
    @Autowired
    private SmartphoneService smartphoneService;

    @GetMapping
    public ResponseEntity<Iterable<Smartphone>> findAll() {
        return new ResponseEntity<>(smartphoneService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Smartphone> createSmartphone(@RequestBody Smartphone sm) {
        return new ResponseEntity<>(smartphoneService.save(sm), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Smartphone> delete(@PathVariable Long id) {
        Optional<Smartphone> smOptional = smartphoneService.findById(id);
        if (!smOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        smartphoneService.remove(id);
        return new ResponseEntity<>(smOptional.get(), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Smartphone> update(@PathVariable Long id, @RequestBody Smartphone sm) {
        Optional<Smartphone> smOptional = smartphoneService.findById(id);
        if (!smOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        sm.setId(id);
        return new ResponseEntity<>(smartphoneService.save(sm), HttpStatus.OK);
    }
}
