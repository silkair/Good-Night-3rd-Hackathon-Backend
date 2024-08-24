package com.example.TecheerTree.Controller;

import com.example.TecheerTree.Entity.Comment;
import com.example.TecheerTree.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishes/{wishId}/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@PathVariable Long wishId, @RequestBody String content) {
        return new ResponseEntity<>(commentService.createComment(wishId, content), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long wishId, Pageable pageable) {
        return new ResponseEntity<>(commentService.getComments(wishId, pageable), HttpStatus.OK);
    }
}
