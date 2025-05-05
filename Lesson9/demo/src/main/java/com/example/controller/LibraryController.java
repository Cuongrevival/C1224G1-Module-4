package com.example.controller;

import com.example.model.Book;
import com.example.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String userId, Model model) {
        try {
            libraryService.registerUser(userId);
            model.addAttribute("message", "User registered successfully");
            return "bookList";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "register";
    }

    @GetMapping("/books")
    public String listBooks(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Book> books = libraryService.listBooks(PageRequest.of(page, 10));
        model.addAttribute("books", books.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", books.getTotalPages());
        return "bookList";
    }

    @GetMapping("/borrow")
    public String showBorrowForm(Model model) {
        model.addAttribute("books", libraryService.listBooks(PageRequest.of(0, 10)).getContent());
        return "borrowForm";
    }

    @PostMapping("/borrow")
    public String borrowBook(@RequestParam Long bookId, @RequestParam String userId, Model model) {
        try {
            Book book = libraryService.borrowBook(bookId, userId);
            model.addAttribute("message", "Book borrowed successfully: " + book.getTitle());
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "borrowForm";
    }

    @GetMapping("/return")
    public String showReturnForm() {
        return "returnForm";
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam String borrowCode, Model model) {
        try {
            Book book = libraryService.returnBook(borrowCode);
            model.addAttribute("message", "Book returned successfully: " + book.getTitle());
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "returnForm";
    }
}
