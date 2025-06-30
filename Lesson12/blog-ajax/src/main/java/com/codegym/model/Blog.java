package com.codegym.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column (columnDefinition = "TEXT")
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    public Blog(Long id, String title, String content, LocalDate createdDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;

    }

    public Blog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }


}
