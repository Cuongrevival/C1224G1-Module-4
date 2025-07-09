package com.codegym.service.impl;

import com.codegym.model.Watch;
import com.codegym.repository.IWatchRepo;
import com.codegym.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WatchServiceImpl implements WatchService {

    @Autowired
    private IWatchRepo watchRepository;

    @Override
    public Page<Watch> findAllPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return watchRepository.findAll(pageable);
    }

    @Override
    public List<Watch> searchByBrand(String keyword) {
        return watchRepository.findByBrandContainingIgnoreCase(keyword);
    }

    @Override
    public Optional<Watch> findById(Long id) {
        return watchRepository.findById(id);
    }

    @Override
    public void save(Watch watch) {
        watchRepository.save(watch);
    }
}

