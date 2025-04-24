package com.codegym.service;

import com.codegym.model.Blog;

import java.util.List;

public interface IBlogService {
    List<Blog> findAll();
    Blog findById(long id);
    void save(Blog blog);
    void delete(Blog blog);
    void update(Blog blog);
}
