package com.codegym.service;

import com.codegym.model.Province;
import com.codegym.repository.IProvinceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProvinceService {
   private final IProvinceRepo provinceRepo;
   @Autowired
    public ProvinceService(IProvinceRepo provinceRepo) {
        this.provinceRepo = provinceRepo;
    }

    public Iterable<Province> findAll() {
        return provinceRepo.findAll();
    }

    public Optional<Province> findById(Long id) {
       return provinceRepo.findById(id);
    }
    public void save(Province province) {
        provinceRepo.save(province);
    }

    public void remove(Long id) {
        provinceRepo.deleteById(id);
    }
}
