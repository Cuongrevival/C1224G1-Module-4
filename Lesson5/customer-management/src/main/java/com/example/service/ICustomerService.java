package com.example.service;

import com.example.model.Customer;

import javax.transaction.SystemException;
import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    Customer findById(int id);
    void save(Customer customer) throws SystemException;
    void update(Customer customer);
    void delete(Customer customer);
}
