package com.example.service;

import com.example.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsForToday() {
        return commentRepository.findByCreatedDate(LocalDate.now());
    }

    @Override
    public void likeComment(Long id) {
        Comment comment = (Comment) commentRepository.findById(id).orElse(null);
        if (comment != null) {
            comment.setLikes(comment.getLikes() + 1);
            commentRepository.save(comment);
        }
    }
}
