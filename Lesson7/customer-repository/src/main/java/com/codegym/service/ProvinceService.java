package com.codegym.service;

import com.codegym.model.Province;
import com.codegym.repository.IProvinceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ProvinceService {
    @Autowired
    private IProvinceRepo provinceRepo;

    public Iterable<Province> getAllProvinces() {
        return provinceRepo.findAll();
    }
    public Optional<Province> getProvinceById(Long id) {
        return provinceRepo.findById(id);
    }
    public void saveProvince(Province province) {
        provinceRepo.save(province);
    }
}
