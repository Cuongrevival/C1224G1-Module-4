package com.example.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "visitor")
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private int visitCount;

    @OneToMany(mappedBy = "visitor")
    private List<BorrowCard> borrowCards;  // Mối quan hệ 1-N với BorrowCard

    public Visitor() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public List<BorrowCard> getBorrowCards() {
        return borrowCards;
    }

    public void setBorrowCards(List<BorrowCard> borrowCards) {
        this.borrowCards = borrowCards;
    }
}
