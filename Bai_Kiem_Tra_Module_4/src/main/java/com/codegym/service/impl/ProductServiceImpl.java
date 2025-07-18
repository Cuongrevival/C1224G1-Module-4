package com.codegym.service.impl;

import com.codegym.model.Product;
import com.codegym.repository.IProductRepo;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private IProductRepo productRepo;
    @Override
    public Page<Product> searchProducts(String name, Double price, Long categoryId, Pageable pageable) {
        return productRepo.search(name, price, categoryId, pageable);
    }

    @Override
    public Product save(Product product) {
        return productRepo.save(product);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            productRepo.deleteAllById(ids);
        }
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepo.findById(id);
    }
}
