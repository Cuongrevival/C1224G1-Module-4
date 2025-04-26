package com.example.repository;

import java.util.List;

public interface IGeneratedRepository<T> {
    List<T> findAll();

    T findById(long id);

    void save(T t);

    void delete(T t);

    void update(T t);
    void insertCustomerByProcedure(String firstName, String lastName, String phone, String address);
}
