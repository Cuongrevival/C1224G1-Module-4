package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.model.Category;
import com.codegym.service.ProductService;
import com.codegym.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    // ✅ 1. Trang danh sách sản phẩm có tìm kiếm + phân trang
    @GetMapping
    public String listProducts(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        Pageable pageable = PageRequest.of(page, 5, Sort.by("id").descending());

        Page<Product> productPage = productService.search(name, price, categoryId, pageable);
        List<Category> categories = categoryService.findAll();

        model.addAttribute("productPage", productPage);
        model.addAttribute("categories", categories);
        model.addAttribute("name", name);
        model.addAttribute("price", price);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("currentPage", page);

        return "product/list";
    }

    // ✅ 2. Hiển thị form thêm mới sản phẩm
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "product/create";
    }

    // ✅ 3. Xử lý thêm mới sản phẩm
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product, Model model) {
        // Validate ở bước View bằng JavaScript hoặc Spring Validator nếu cần
        productService.save(product);
        return "redirect:/products";
    }

    // ✅ 4. Hiển thị form cập nhật sản phẩm
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Product> productOpt = productService.findById(id);
        if (productOpt.isPresent()) {
            model.addAttribute("product", productOpt.get());
            model.addAttribute("categories", categoryService.findAll());
            return "product/edit";
        }
        return "redirect:/products";
    }

    // ✅ 5. Xử lý xóa nhiều sản phẩm (checkbox)
    @PostMapping("/delete")
    public String deleteProducts(@RequestParam("ids") List<Long> ids) {
        productService.deleteByIds(ids);
        return "redirect:/products";
    }
}
