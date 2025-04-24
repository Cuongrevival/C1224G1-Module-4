package com.example.repository;

import com.example.model.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CustomerRepository implements ICustomerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> query = entityManager.createQuery("SELECT c FROM Customer c", Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findById(long id) {
        TypedQuery<Customer> query = entityManager.createQuery("SELECT c FROM Customer c WHERE c.id = :id", Customer.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void save(Customer customer) {
        if (customer.getId() == 0) {
            entityManager.persist(customer);
        } else {
            entityManager.merge(customer);
        }
    }

    @Override
    public void delete(Customer customer) {
        TypedQuery<Customer> query = entityManager.createQuery("SELECT c FROM Customer c WHERE c.id = :id", Customer.class);
        query.setParameter("id", customer.getId());
        Customer c = query.getSingleResult();
        entityManager.remove(c);
    }

    @Override
    public void update(Customer customer) {
        TypedQuery<Customer> query = entityManager.createQuery("SELECT c FROM Customer c WHERE c.id = :id", Customer.class);
        query.setParameter("id", customer.getId());
        Customer c = query.getSingleResult();
        c.setName(customer.getName());
        c.setPhone(customer.getPhone());
        c.setAddress(customer.getAddress());
    }
}
