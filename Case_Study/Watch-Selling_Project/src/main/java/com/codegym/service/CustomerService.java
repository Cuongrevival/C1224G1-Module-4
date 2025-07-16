package com.codegym.service;

import com.codegym.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer register(Customer customer);
    Customer getCurrentCustomer();
    void deleteById(Long id);
    void save(Customer customer);
    Customer findById(Long id);
}
