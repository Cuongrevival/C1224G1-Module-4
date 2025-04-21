package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.IProductService;
import com.codegym.service.ProductServiceImp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService = new ProductServiceImp();

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/update")
    public String showUpdateForm(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "update";
    }

    @PostMapping("/{id}/update")
    public String update(@ModelAttribute Product product, @PathVariable int id) {
        product.setId(id);
        productService.updateProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/delete")
    public String showDeleteForm(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "delete";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute Product product, RedirectAttributes redirect) {
        productService.deleteProduct(product);
        redirect.addFlashAttribute("message", "Xoá thành công!");
        return "redirect:/products";
    }

    @GetMapping("/search")
    public String search(@RequestParam String name, Model model) {
        Product product = productService.getProductByName(name);
        if (product != null) {
            model.addAttribute("products", java.util.Arrays.asList(product));
        }
        model.addAttribute("message", (product != null) ? "Tìm thấy sản phẩm" : "Không tìm thấy sản phẩm");
        return "search";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "view";
    }
}
