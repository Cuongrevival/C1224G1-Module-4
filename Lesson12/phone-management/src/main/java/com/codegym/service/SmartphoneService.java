package com.codegym.service;

import com.codegym.model.Smartphone;
import com.codegym.repository.ISmartPhoneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class SmartphoneService {
    @Autowired
    private ISmartPhoneRepo smartPhone;

    public Iterable<Smartphone> findAll() {
        return smartPhone.findAll();
    }
    public Optional<Smartphone> findById(Long id) {
        return smartPhone.findById(id);
    }
    public Smartphone save(Smartphone sm) {
        return smartPhone.save(sm);
    }
    public void remove(Long id) {
        smartPhone.deleteById(id);
    }
}
