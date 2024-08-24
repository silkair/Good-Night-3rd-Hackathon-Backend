package com.example.TecheerTree.Service;

import com.example.TecheerTree.Entity.Comment;
import com.example.TecheerTree.Entity.Wish;
import com.example.TecheerTree.Repository.CommentRepository;
import com.example.TecheerTree.Repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private WishRepository wishRepository;

    // 댓글 생성
    public Comment createComment(Long wishId, String content) {
        Wish wish = wishRepository.findById(wishId)
                .orElseThrow(() -> new EntityNotFoundException("소원을 찾을 수 없습니다."));

        Comment comment = new Comment();
        comment.setWish(wish);
        comment.setContent(content);
        comment.setCreatedAt(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    // 댓글 삭제 (soft delete)
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다."));
        comment.setDeletedAt(LocalDateTime.now());
        commentRepository.save(comment);
    }

    // 댓글 조회
    public List<Comment> getComments(Long wishId, Pageable pageable) {
        Wish wish = wishRepository.findById(wishId)
                .orElseThrow(() -> new EntityNotFoundException("소원을 찾을 수 없습니다."));
        return commentRepository.findByWishAndDeletedAtIsNull(wish, pageable);
    }
}
