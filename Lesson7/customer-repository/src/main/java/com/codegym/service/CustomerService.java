package com.codegym.service;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.repository.ICustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private ICustomerRepo customerRepo;

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepo.findById(id);
    }
   public void saveCustomer(Customer customer) {
        customerRepo.save(customer);
   }
    public void deleteCustomer(Long id) {
        customerRepo.deleteById(id);
    }
    public Iterable<Customer> findCustomerByProvince(Province province) {
        return customerRepo.findCustomerByProvince(province);
    }
}
