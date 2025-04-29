package com.example.service;

import com.example.model.Book;
import com.example.model.BorrowCard;
import com.example.model.Visitor;
import com.example.repository.IBookRepository;
import com.example.repository.IBorrowCardRepository;
import com.example.repository.IVisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private IBorrowCardRepository borrowCardRepository;

    @Autowired
    private IVisitorRepository visitorRepository;

    public void registerUser(String userId) throws Exception {
        if (visitorRepository.findByUserId(userId).isPresent()) {
            throw new Exception("User already registered");
        }

        Visitor newVisitor = new Visitor();
        newVisitor.setUserId(userId);
        newVisitor.setVisitCount(0);
        visitorRepository.save(newVisitor);
    }

    public Book borrowBook(Long bookId, String userId) throws Exception {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (!bookOptional.isPresent()) {
            throw new Exception("Book not found");
        }

        Book book = bookOptional.get();
        if (book.getQuantity() <= 0) {
            throw new Exception("No copies left to borrow");
        }

        Optional<Visitor> visitorOptional = visitorRepository.findByUserId(userId);
        if (!visitorOptional.isPresent()) {
            throw new Exception("User not found");
        }

        Visitor visitor = visitorOptional.get();

        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        // Tạo thẻ mượn
        BorrowCard borrowCard = new BorrowCard();
        borrowCard.setBook(book);
        borrowCard.setVisitor(visitor);
        borrowCard.setBorrowCode(generateBorrowCode());
        borrowCard.setBorrowDate(LocalDate.now());
        borrowCardRepository.save(borrowCard);

        return book;
    }

    public Book returnBook(String borrowCode) throws Exception {
        Optional<BorrowCard> borrowCardOptional = borrowCardRepository.findByBorrowCode(borrowCode);
        if (!borrowCardOptional.isPresent()) {
            throw new Exception("Invalid borrow code");
        }

        BorrowCard borrowCard = borrowCardOptional.get();
        Book book = borrowCard.getBook();

        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);

        borrowCardRepository.delete(borrowCard);

        return book;
    }

    private String generateBorrowCode() {
        return String.format("%05d", (int) (Math.random() * 100000));
    }
    public Page<Book> listBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
}
