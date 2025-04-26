package com.codegym.service;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.repository.ICustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private final ICustomerRepo customerRepo;

    @Autowired
    public CustomerService(ICustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }
    public Iterable<Customer> findAll() {
        return customerRepo.findAll();
    }

    public Optional<Customer> findById(Long id) {
        return customerRepo.findById(id);
    }
    public void save(Customer customer) {
        customerRepo.save(customer);
    }

    public void remove(Long id) {
        customerRepo.deleteById(id);
    }

    public Iterable<Customer> findAllByProvince(Province province) {
        return customerRepo.findAllByProvince(province);
    }
}
