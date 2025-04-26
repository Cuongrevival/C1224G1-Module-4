package com.example.controller;

import com.example.model.Blog;
import com.example.model.Category;
import com.example.service.BlogService;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    private final BlogService blogService;
    private final CategoryService categoryService;

    @Autowired
    public BlogController(BlogService blogService, CategoryService categoryService) {
        this.blogService = blogService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listBlogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long categoryId,
            Model model) {
        Page<Blog> blogs;
        if (keyword != null && !keyword.trim().isEmpty()) {
            blogs = blogService.search(keyword, page, size);
            model.addAttribute("keyword", keyword);
        } else if (categoryId != null) {
            blogs = blogService.getByCategory(categoryId, page, size);
            model.addAttribute("categoryId", categoryId);
        } else {
            blogs = blogService.findAll(page, size);
        }

        List<Category> categories = categoryService.getAll();
        model.addAttribute("blogs", blogs);
        model.addAttribute("categories", categories);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", blogs.getTotalPages());
        return "blogs/list";
    }

    @GetMapping("/{id}")
    public String viewBlog(@PathVariable Long id, Model model) {
        Optional<Blog> blog = blogService.getById(id);
        if (blog.isPresent()) {
            model.addAttribute("blog", blog.get());
            return "blogs/view";
        } else {
            model.addAttribute("error", "Bài viết không tồn tại");
            return "blogs/list";
        }
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("categories", categoryService.getAll());
        return "blogs/form";
    }

    @PostMapping
    public String createBlog(
            @ModelAttribute("blog") Blog blog, @RequestParam Long categoryId, Model model) {
        try {
            blogService.create(blog, categoryId);
            return "redirect:/blogs";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("categories", categoryService.getAll());
            return "blogs/form";
        }
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Blog> blog = blogService.getById(id);
        if (blog.isPresent()) {
            model.addAttribute("blog", blog.get());
            model.addAttribute("categories", categoryService.getAll());
            return "blogs/edit";
        } else {
            model.addAttribute("error", "Bài viết không tồn tại");
            return "redirect:/blogs";
        }
    }

    @PostMapping("/{id}")
    public String updateBlog(
            @PathVariable Long id,
            @ModelAttribute("blog") Blog blogDetails,
            @RequestParam Long categoryId,
            Model model) {
        try {
            blogService.update(id, blogDetails, categoryId);
            return "redirect:/blogs";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("categories", categoryService.getAll());
            return "blogs/edit";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteBlog(@PathVariable Long id, Model model) {
        try {
            blogService.delete(id);
            return "redirect:/blogs";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/blogs";
        }
    }
}