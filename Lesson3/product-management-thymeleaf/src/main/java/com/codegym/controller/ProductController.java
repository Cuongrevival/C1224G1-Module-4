package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.IProductService;
import com.codegym.service.ProductServiceImp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService;

    public ProductController() {
        productService = new ProductServiceImp();
    }

    @GetMapping("")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "create";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/update")
    public String showUpdateForm(@PathVariable int id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "update";
    }

    @PostMapping("/{id}/update")
    public String updateProduct(@PathVariable int id, @ModelAttribute Product product) {
        product.setId(id);
        productService.updateProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/delete")
    public String showDeleteForm(@PathVariable int id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "delete";
    }

    @PostMapping("/delete")
    public String deleteProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        productService.deleteProduct(product);
        redirectAttributes.addFlashAttribute("message", "Product deleted successfully");
        return "redirect:/products";
    }

    // Tìm kiếm sản phẩm
    @GetMapping("/search")
    public String searchProduct(@RequestParam("name") String name, Model model) {
        Product product = productService.getProductByName(name);
        if (product != null) {
            model.addAttribute("products", Collections.singletonList(product));
            model.addAttribute("message", "Sản phẩm tìm thấy!");
        } else {
            model.addAttribute("message", "Không tìm thấy sản phẩm nào!");
        }
        return "search";
    }

    @GetMapping("/{id}/view")
    public String viewProduct(@PathVariable int id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "view";
    }
}
