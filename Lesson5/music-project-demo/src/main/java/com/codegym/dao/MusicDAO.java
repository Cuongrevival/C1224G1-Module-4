package com.codegym.dao;

import com.codegym.model.Music;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class MusicDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Music music) {
        entityManager.persist(music);
    }

    public List<Music> findAll() {
        return entityManager.createQuery("SELECT m FROM Music m", Music.class).getResultList();
    }

    public Music findById(int id) {
        return entityManager.find(Music.class, id);
    }

    public void update(Music music) {
        entityManager.merge(music);
    }

    public void delete(int id) {
        Music music = findById(id);
        if (music != null) {
            entityManager.remove(music);
        }
    }
}
