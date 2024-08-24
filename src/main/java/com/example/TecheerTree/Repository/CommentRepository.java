package com.example.TecheerTree.Repository;

import com.example.TecheerTree.Entity.Comment;
import com.example.TecheerTree.Entity.Wish;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByWishAndDeletedAtIsNull(Wish wish, Pageable pageable);
}
