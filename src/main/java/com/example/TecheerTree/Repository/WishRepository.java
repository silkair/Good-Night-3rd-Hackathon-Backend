package com.example.TecheerTree.Repository;

import com.example.TecheerTree.Entity.Wish;
import com.example.TecheerTree.Entity.enums.WishCategory;
import com.example.TecheerTree.Entity.enums.WishStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishRepository extends JpaRepository<Wish, Long> {
    List<Wish> findByStatus(WishStatus status);
    List<Wish> findByTitleContainingOrContentContainingAndCategory(String titleKeyword, String contentKeyword, WishCategory category);
    List<Wish> findByDeletedAtIsNullAndStatus(WishStatus status, Pageable pageable);
}
