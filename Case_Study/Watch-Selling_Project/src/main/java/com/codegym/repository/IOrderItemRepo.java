package com.codegym.repository;

import com.codegym.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderItemRepo extends JpaRepository<OrderItem,Long> {
}
