package com.example.repository;

import com.example.model.BorrowCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IBorrowCardRepository extends JpaRepository<BorrowCard, Long> {
    Optional<BorrowCard> findByBorrowCode(String borrowCode);
}
