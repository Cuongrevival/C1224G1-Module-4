package com.example.repository;

import com.example.model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IVisitorRepository extends JpaRepository<Visitor, Long> {
    Optional<Visitor> findByUserId(String userId);
}
