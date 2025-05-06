package com.codegym.service;

import com.codegym.model.Post;
import com.codegym.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private IPostRepository postRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }
    public List<Post> findAllByCategoryId(Long categoryId) {
        return postRepository.findAllByCategoryId(categoryId);
    }
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }
}
