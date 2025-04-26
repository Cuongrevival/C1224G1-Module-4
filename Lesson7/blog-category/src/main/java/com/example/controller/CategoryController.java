package com.example.controller;

import com.example.model.Category;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listCategories(Model model) {
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        return "categories/list";
    }

    @GetMapping("/{id}")
    public String viewCategory(@PathVariable Long id, Model model) {
        Optional<Category> category = categoryService.getById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "categories/view";
        } else {
            model.addAttribute("error", "Danh mục không tồn tại");
            return "categories/list";
        }
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("category", new Category());
        return "categories/form";
    }

    @PostMapping
    public String createCategory(@ModelAttribute("category") Category category, Model model) {
        try {
            categoryService.create(category);
            return "redirect:/categories";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "categories/form";
        }
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Category> category = categoryService.getById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "categories/form";
        } else {
            model.addAttribute("error", "Danh mục không tồn tại");
            return "redirect:/categories";
        }
    }

    @PostMapping("/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute("category") Category categoryDetails,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "categories/form";
        }
        try {
            categoryService.update(id, categoryDetails);
            return "redirect:/categories";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "categories/form";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteCategory(@PathVariable Long id, Model model) {
        try {
            categoryService.delete(id);
            return "redirect:/categories";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/categories";
        }
    }
}