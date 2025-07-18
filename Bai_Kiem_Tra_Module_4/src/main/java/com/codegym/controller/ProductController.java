package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.CategoryService;
import com.codegym.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String listProducts(@RequestParam(required = false) String name,
                               @RequestParam(required = false) Double price,
                               @RequestParam(required = false) Long categoryId,
                               @RequestParam(defaultValue = "0") int page,
                               Model model,
                               @ModelAttribute("message") String message) {

        Pageable pageable = PageRequest.of(page, 5);
        Page<Product> productPage = productService.searchProducts(name, price, categoryId, pageable);

        model.addAttribute("page", productPage);
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("name", name);
        model.addAttribute("price", price);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("message", message);

        return "product/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "product/create";
    }

    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute("product") Product product,
                                BindingResult bindingResult,
                                Model model,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || (product.getPrice() != null && product.getPrice() < 100000)) {
            if (product.getPrice() != null && product.getPrice() < 100000) {
                bindingResult.rejectValue("price", "error.product", "Giá phải từ 100.000 trở lên");
            }
            model.addAttribute("categories", categoryService.findAll());
            return "product/create";
        }

        productService.save(product);
        redirectAttributes.addFlashAttribute("message", "✅ Thêm sản phẩm thành công!");
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Product> optionalProduct = productService.findById(id);
        if (!optionalProduct.isPresent()) {
            return "redirect:/products";
        }

        model.addAttribute("product", optionalProduct.get());
        model.addAttribute("categories", categoryService.findAll());
        return "product/edit";
    }

    @PostMapping("/edit")
    public String updateProduct(@Valid @ModelAttribute("product") Product product,
                                BindingResult bindingResult,
                                Model model,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || (product.getPrice() != null && product.getPrice() < 100000)) {
            if (product.getPrice() != null && product.getPrice() < 100000) {
                bindingResult.rejectValue("price", "error.product", "Giá phải từ 100.000 trở lên");
            }
            model.addAttribute("categories", categoryService.findAll());
            return "product/edit";
        }

        productService.save(product);
        redirectAttributes.addFlashAttribute("message", "✅ Cập nhật sản phẩm thành công!");
        return "redirect:/products";
    }

    // ✅ Xóa nhiều sản phẩm được chọn
    @PostMapping("/delete")
    public String deleteSelected(@RequestParam("ids") List<Long> ids,
                                 RedirectAttributes redirectAttributes) {
        if (ids != null && !ids.isEmpty()) {
            productService.deleteByIds(ids);
            redirectAttributes.addFlashAttribute("message", "🗑️ Đã xóa " + ids.size() + " sản phẩm!");
        } else {
            redirectAttributes.addFlashAttribute("message", "⚠️ Vui lòng chọn sản phẩm để xóa!");
        }
        return "redirect:/products";
    }
}
