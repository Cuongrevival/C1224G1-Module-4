package com.example.repository;

import com.example.model.Blog;
import com.example.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBlogRepository extends JpaRepository<Blog, Long> {
    Page<Blog> findAll(Pageable pageable);

    @Query("SELECT b FROM Blog b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(b.content) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Blog> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
    List<Blog> findByCategory(Category category);
    Page<Blog> findByCategory(Pageable pageable, Category category);
}
