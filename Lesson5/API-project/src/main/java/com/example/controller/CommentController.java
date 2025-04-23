package com.example.controller;

import com.example.model.Comment;
import com.example.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/comment")
public class CommentController {

    private final ICommentService commentService;

    @Autowired
    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/submit-comment")
    public ResponseEntity<Void> submitComment(@RequestBody Comment comment) {
        commentService.saveComment(comment);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-comments")
    public ResponseEntity<List<Comment>> getComments() {
        List<Comment> comments = commentService.getCommentsForToday();
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/like-comment")
    public ResponseEntity<Void> likeComment(@RequestBody Map<String, Long> payload) {
        commentService.likeComment(payload.get("id"));
        return ResponseEntity.ok().build();
    }

    @Value("${nasa.api.key}")
    private String apiKey;

    @GetMapping
    public String showCommentPage(Model model) {
        model.addAttribute("apiKey", apiKey);
        return "comment"; // templates/comment.html
    }
}
