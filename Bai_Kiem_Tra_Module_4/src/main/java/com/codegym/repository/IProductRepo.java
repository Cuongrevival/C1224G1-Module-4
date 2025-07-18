package com.codegym.repository;

import com.codegym.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepo extends JpaRepository<Product,Long> {
    Page<Product> findByNameContainingIgnoreCaseAndCategory_CidAndPriceGreaterThanEqual(
            String name, Long categoryId, Double price, Pageable pageable
    );

    Page<Product> findByNameContainingIgnoreCaseAndCategory_Cid(
            String name, Long categoryId, Pageable pageable
    );

    Page<Product> findByNameContainingIgnoreCaseAndPriceGreaterThanEqual(
            String name, Double price, Pageable pageable
    );

    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
