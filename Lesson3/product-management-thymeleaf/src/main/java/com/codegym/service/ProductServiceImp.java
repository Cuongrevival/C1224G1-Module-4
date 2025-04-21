package com.codegym.service;

import com.codegym.model.Product;

import java.util.*;

public class ProductServiceImp implements IProductService {
    // Sử dụng ConcurrentHashMap nếu cần xử lý đồng thời
    private static final Map<Integer, Product> products = new HashMap<>();
    private static final Map<String, Product> productsByName = new HashMap<>(); // Tạo thêm map tìm kiếm theo tên
    private static int autoIncrementId = 5;

    static {
        addInitialProduct(1, "Laptop Dell Inspiron", 15000000.0, 10);
        addInitialProduct(2, "Chuột không dây Logitech", 350000.0, 25);
        addInitialProduct(3, "Bàn phím cơ Keychron K6", 1800000.0, 15);
        addInitialProduct(4, "Màn hình LG 24 inch Full HD", 3200000.0, 8);
    }

    private static void addInitialProduct(int id, String name, double price, int quantity) {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setCurrentQuantity(quantity);
        products.put(id, product);
        productsByName.put(name, product);  // Cập nhật map tìm kiếm theo tên
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product getProductById(int id) {
        return products.get(id);
    }

    @Override
    public void updateProduct(Product product) {
        products.put(product.getId(), product);
        productsByName.put(product.getName(), product);  // Cập nhật map tìm kiếm theo tên khi sửa
    }

    @Override
    public void deleteProduct(Product product) {
        products.remove(product.getId());
        productsByName.remove(product.getName());  // Xóa khỏi map tìm kiếm theo tên
    }

    @Override
    public void saveProduct(Product product) {
        if (product.getId() == 0 || !products.containsKey(product.getId())) {
            product.setId(autoIncrementId++);
        }
        products.put(product.getId(), product);
        productsByName.put(product.getName(), product);  // Cập nhật map tìm kiếm theo tên
    }

    @Override
    public Product getProductByName(String name) {
        return productsByName.get(name);  // Tìm sản phẩm nhanh hơn từ map theo tên
    }
}
