package com.codegym.service;

import com.codegym.model.Product;
import java.util.*;

public class ProductServiceImp implements IProductService {
    private static final Map<Integer, Product> products = new HashMap<>();
    private static int autoIncrementId = 5;

    static {
        addInitialProduct(1, "Laptop", 15000000.0, "Laptop cao cấp", "Dell");
        addInitialProduct(2, "Chuột", 350000.0, "Chuột không dây", "Logitech");
    }

    private static void addInitialProduct(int id, String name, double price, String desc, String manu) {
        Product p = new Product();
        p.setId(id);
        p.setName(name);
        p.setPrice(price);
        p.setDescription(desc);
        p.setManufacturer(manu);
        products.put(id, p);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public Product getProductById(int id) {
        return products.get(id);
    }

    public void saveProduct(Product product) {
        if (product.getId() == 0) product.setId(autoIncrementId++);
        products.put(product.getId(), product);
    }

    public void updateProduct(Product product) {
        products.put(product.getId(), product);
    }

    public void deleteProduct(Product product) {
        products.remove(product.getId());
    }

    public Product getProductByName(String name) {
        for (Product p : products.values()) {
            if (p.getName().equalsIgnoreCase(name)) return p;
        }
        return null;
    }
}
