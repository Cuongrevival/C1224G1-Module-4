package com.codegym.service.impl;

import com.codegym.model.Customer;
import com.codegym.repository.ICustomerRepo;
import com.codegym.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private ICustomerRepo customerRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Customer register(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setRole(com.codegym.model.Role.USER);
        return customerRepo.save(customer);
    }

    @Override
    public Customer getCurrentCustomer() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return customerRepo.findByUsername(username).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        customerRepo.deleteById(id);
    }

    @Override
    public void save(Customer customer) {
        customerRepo.save(customer);
    }

    public List<Customer> findAll() {
        return customerRepo.findAll();
    }
    public Customer findById(Long id) {
        return customerRepo.findById(id).orElse(null);
    }
}



