package com.codegym.repository;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerRepo extends JpaRepository<Customer, Long> {
    Iterable<Customer> findCustomerByProvince(Province province);
    Optional<Customer> findById(Long id);
    void deleteById(Long id);
}
