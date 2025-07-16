package com.codegym.service;

import com.codegym.model.Watch;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface WatchService {
    Page<Watch> findAllPaged(int page, int size);
    List<Watch> searchByBrand(String keyword);
    Optional<Watch> findById(Long id);
    void save(Watch watch);
    void delete(Watch watch);
}
