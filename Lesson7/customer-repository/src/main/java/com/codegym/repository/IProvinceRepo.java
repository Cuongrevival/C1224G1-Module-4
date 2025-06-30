package com.codegym.repository;

import com.codegym.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProvinceRepo extends JpaRepository<Province, Long> {
    Optional<Province> findById(Long id);

}
