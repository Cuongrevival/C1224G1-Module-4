package com.codegym.service;

import com.codegym.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Page<Product> searchProducts(String name, Double price, Long categoryId, Pageable pageable);
    Product save(Product product);
    void deleteByIds(List<Long> ids);
    Optional<Product> findById(Long id);
}
