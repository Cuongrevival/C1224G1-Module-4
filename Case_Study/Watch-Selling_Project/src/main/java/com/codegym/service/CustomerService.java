package com.codegym.service;

import com.codegym.model.Customer;

public interface CustomerService {
    Customer register(Customer customer);
    Customer getCurrentCustomer();
    Customer update(Customer customer);
    void deleteById(Long id);
}
