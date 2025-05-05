package com.codegym.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> products = new HashMap<>();

    public Cart(Map<Product, Integer> products) {
        this.products = products;
    }

    public Cart() {
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }


    private Map.Entry<Product, Integer> getItems(Product product) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (entry.getKey().getId().equals(product.getId())) {
                return entry;
            }
        }
        return null;
    }

    private boolean checkItems(Product product) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (entry.getKey().getId().equals(product.getId())) {
                return true;
            }
        }
        return false;
    }

    public Integer countProducts() {
        int productCount = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                productCount += entry.getValue();

        }
        return productCount;
    }

    public Integer countItem() {
        return products.size();
    }

    public double countPrice() {
        double price = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                price += entry.getKey().getPrice() * entry.getValue();

        }
        return price;
    }

    public void addProduct(Product product) {
        if (!checkItems(product)) {
            products.put(product, 1);
        } else {
            Map.Entry<Product, Integer> entry = getItems(product);
            int count = entry.getValue() + 1;
            products.replace(entry.getKey(), count);
        }
    }
}
