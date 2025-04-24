package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private IBlogRepository blogRepository;

    @GetMapping
    public String showBlogList(Model model) {
        model.addAttribute("blogs", blogRepository.findAll());
        return "list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("blog", new Blog());
        return "create";
    }

    @PostMapping("/create")
    public String createBlog(@ModelAttribute("blog") Blog blog) {
        blogRepository.save(blog);
        return "redirect:/blog";
    }

    @GetMapping("/{id}")
    public String viewBlog(@PathVariable("id") long id, Model model) {
        Blog blog = blogRepository.findById(id);
        model.addAttribute("blog", blog);
        return "view";
    }

    // Hiển thị form cập nhật blog
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Blog blog = blogRepository.findById(id);
        model.addAttribute("blog", blog);
        return "edit";
    }

    // Xử lý cập nhật blog
    @PostMapping("/update")
    public String updateBlog(@ModelAttribute("blog") Blog blog) {
        blogRepository.update(blog);
        return "redirect:/blog";
    }

    // Xoá blog
    @GetMapping("/{id}/delete")
    public String deleteBlog(@PathVariable("id") long id) {
        Blog blog = blogRepository.findById(id);
        blogRepository.delete(blog);
        return "redirect:/blog";
    }
}
