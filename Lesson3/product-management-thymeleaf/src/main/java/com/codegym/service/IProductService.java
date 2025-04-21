package com.codegym.service;

import com.codegym.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    Product getProductById(int id);
    void updateProduct(Product product);
    void deleteProduct(Product product);
    void saveProduct(Product product);
    Product getProductByName(String name);
}
