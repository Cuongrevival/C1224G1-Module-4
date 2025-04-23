package com.example.service;

import com.example.model.Comment;

import java.util.List;

public interface ICommentService {
    void saveComment(Comment comment);
    List<Comment> getCommentsForToday();
    void likeComment(Long id);
}
