package com.codegym.service;

import com.codegym.model.Customer;
import com.codegym.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private ICustomerRepository iCustomerRepository;

    public Iterable<Customer> findAll() {
        return iCustomerRepository.findAll();
    }

    public Optional<Customer> findById(Long id) {
        return iCustomerRepository.findById(id);
    }

    public Customer save(Customer customer) {
        return iCustomerRepository.save(customer);
    }

    public void remove(Long id) {
        iCustomerRepository.deleteById(id);
    }
}