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
    private IProductRepo productRepository;

    @Override
    public Page<Product> search(String name, Double price, Long categoryId, Pageable pageable) {
        if (name == null) name = "";
        if (price == null && categoryId == null) {
            return productRepository.findByNameContainingIgnoreCase(name, pageable);
        } else if (price == null) {
            return productRepository.findByNameContainingIgnoreCaseAndCategory_Cid(name, categoryId, pageable);
        } else if (categoryId == null) {
            return productRepository.findByNameContainingIgnoreCaseAndPriceGreaterThanEqual(name, price, pageable);
        } else {
            return productRepository.findByNameContainingIgnoreCaseAndCategory_CidAndPriceGreaterThanEqual(
                    name, categoryId, price, pageable
            );
        }
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        productRepository.deleteAllById(ids);
    }
}
