package com.codegym.repository;

import com.codegym.model.Smartphone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISmartPhoneRepo extends JpaRepository<Smartphone, Long> {
}
