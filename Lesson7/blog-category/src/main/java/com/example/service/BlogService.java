package com.example.service;

import com.example.model.Blog;
import com.example.model.Category;
import com.example.repository.IBlogRepostory;
import com.example.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class BlogService {
    private final IBlogRepostory blogRepository;
    private final ICategoryRepository categoryRepository;
    @Autowired
    public BlogService(IBlogRepostory blogRepostory, ICategoryRepository categoryRepository) {
        this.blogRepository = blogRepostory;
        this.categoryRepository = categoryRepository;
    }
    public Page<Blog> findAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        return blogRepository.findAll(pageable);
    }
    public Page<Blog> search(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        return blogRepository.searchByKeyword(keyword, pageable);
    }

    public Optional<Blog> getById(Long id){
        return blogRepository.findById(id);
    }

    public Page<Blog> getByCategory(Long categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());

        if (categoryId == null) {
            return blogRepository.findAll(pageable);
        }

        Optional<Category> category = categoryRepository.findById(categoryId);
        return category.map(c -> blogRepository.findByCategory(pageable, c))
                .orElse(Page.empty());
    }

    public void create(Blog blog, Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            blog.setCategory(category.get());
            blogRepository.save(blog);
            return;
        }
        throw new IllegalArgumentException("Danh mục không tồn tại");
    }

    public void update(Long id, Blog blogDetails, Long categoryId) {
        Optional<Blog> blogOptional = blogRepository.findById(id);
        if (blogOptional.isPresent()) {
            Blog blog = blogOptional.get();
            blog.setTitle(blogDetails.getTitle());
            blog.setContent(blogDetails.getContent());
            blog.setCreatedDate(blogDetails.getCreatedDate());
            Optional<Category> category = categoryRepository.findById(categoryId);
            if (category.isPresent()) {
                blog.setCategory(category.get());
            } else {
                throw new IllegalArgumentException("Danh mục không tồn tại");
            }
            blogRepository.save(blog);
        }
    }

    public void delete(Long id) {
        if (blogRepository.existsById(id)) {
            blogRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Bài viết không tồn tại");
        }
    }
}
