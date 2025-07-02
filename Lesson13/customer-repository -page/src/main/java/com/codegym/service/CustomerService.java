package com.codegym.service;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.repository.ICustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private final ICustomerRepo customerRepo;

    @Autowired
    public CustomerService(ICustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
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

    public Page<Customer> findAll(Pageable pageable) {
        return customerRepo.findAll(pageable);
    }

    public Page<Customer> findAllByName(Pageable pageable, String name) {
        return customerRepo.findAllByFirstNameContaining(pageable, name);
    }
}
