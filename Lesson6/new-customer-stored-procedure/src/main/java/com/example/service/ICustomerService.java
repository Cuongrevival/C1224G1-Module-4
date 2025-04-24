package com.example.service;

import com.example.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    Customer findById(long id);
    void save(Customer customer);
    void delete(Customer customer);
    void update(Customer customer);
}
