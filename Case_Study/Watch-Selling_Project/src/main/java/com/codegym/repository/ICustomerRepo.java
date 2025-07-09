package com.codegym.repository;

import com.codegym.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerRepo extends JpaRepository<Customer,Long> {
    Optional<Customer> findByUsername(String name);
}
