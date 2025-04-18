package com.codegym.quanlikhach.service;

import com.codegym.quanlikhach.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();


    Customer findById(Long id);

    void save(Customer customer);
}
