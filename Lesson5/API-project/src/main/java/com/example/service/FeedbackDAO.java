package com.example.service;

import com.example.model.Feedback;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class FeedbackDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Feedback feedback) {
        entityManager.merge(feedback);
    }

    public List<Feedback> findAll() {
        return entityManager.createQuery("from Feedback", Feedback.class).getResultList();
    }

    public Feedback findById(int id) {
        return entityManager.find(Feedback.class, id);
    }

    public void delete(int id) {
        Feedback feedback = entityManager.find(Feedback.class, id);
        if (feedback != null) {
            entityManager.remove(feedback);
        }
    }
}
