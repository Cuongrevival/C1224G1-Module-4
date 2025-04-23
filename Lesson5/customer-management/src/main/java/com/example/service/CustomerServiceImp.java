package com.example.service;

import com.example.model.Customer;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class CustomerServiceImp implements ICustomerService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Customer> findAll() {
        String queryStr = "SELECT c FROM Customer c";
        TypedQuery<Customer> query = entityManager.createQuery(queryStr, Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findById(int id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    @Transactional
    public void save(Customer customer) {
        if (customer.getId() == 0) {
            entityManager.persist(customer);
        } else {
            entityManager.merge(customer);
        }
    }

    @Override
    @Transactional
    public void update(Customer customer) {
        entityManager.merge(customer);
    }

    @Override
    @Transactional
    public void delete(Customer customer) {
        Customer found = entityManager.find(Customer.class, customer.getId());
        if (found != null) {
            entityManager.remove(found);
        }
    }
}
