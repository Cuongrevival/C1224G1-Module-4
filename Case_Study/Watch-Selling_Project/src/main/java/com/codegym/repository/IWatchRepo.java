package com.codegym.repository;

import com.codegym.model.Watch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IWatchRepo extends JpaRepository<Watch, Long> {
    List<Watch> findByBrandContainingIgnoreCase(String keyword);
}
