package com.codegym.service;

import com.codegym.model.Product;
import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    Product getProductById(int id);
    void saveProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Product product);
    Product getProductByName(String name);
}
