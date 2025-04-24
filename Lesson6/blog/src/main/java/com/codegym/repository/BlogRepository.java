package com.codegym.repository;

import com.codegym.model.Blog;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class BlogRepository implements IBlogRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Blog> findAll() {
        TypedQuery<Blog> query = entityManager.createQuery("SELECT b FROM Blog b", Blog.class);
        return query.getResultList();
    }

    @Override
    public Blog findById(long id) {
        TypedQuery<Blog> query = entityManager.createQuery("SELECT b FROM Blog b WHERE b.id = :id", Blog.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void save(Blog blog) {
        TypedQuery<Blog> query = entityManager.createQuery("SELECT b FROM Blog b WHERE b.title = :title", Blog.class);
        query.setParameter("title", blog.getTitle());
        if (query.getResultList().size() > 0) {
            throw new IllegalArgumentException("Title already exists");
        }
        entityManager.persist(blog);
    }

    @Override
    public void delete(Blog blog) {
        TypedQuery<Blog> query = entityManager.createQuery("SELECT b FROM Blog b WHERE b.id = :id", Blog.class);
        query.setParameter("id", blog.getId());
        Blog blogToDelete = query.getSingleResult();
        entityManager.remove(blogToDelete);
    }

    @Override
    public void update(Blog blog) {
        TypedQuery<Blog> query = entityManager.createQuery("SELECT b FROM Blog b WHERE b.id = :id", Blog.class);
        query.setParameter("id", blog.getId());
        Blog blogToUpdate = query.getSingleResult();
        blogToUpdate.setTitle(blog.getTitle());
        blogToUpdate.setContent(blog.getContent());
        blogToUpdate.setAuthorName(blog.getAuthorName());
        blogToUpdate.setCreatedAt(blog.getCreatedAt());
        blogToUpdate.setUpdatedAt(blog.getUpdatedAt());
        blogToUpdate.setStatus(blog.getStatus());
        blogToUpdate.setCategory(blog.getCategory());
    }
}
