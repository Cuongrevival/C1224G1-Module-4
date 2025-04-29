package org.example.repository;

import org.example.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFeedBackRepository extends JpaRepository<Feedback, Long> {
}
