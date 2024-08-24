package com.example.TecheerTree.Service;

import com.example.TecheerTree.Entity.Wish;
import com.example.TecheerTree.Entity.enums.WishCategory;
import com.example.TecheerTree.Entity.enums.WishStatus;
import com.example.TecheerTree.Repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WishService {

    @Autowired
    private WishRepository wishRepository;

    public Wish createWish(Wish wish) {
        if (wish.getTitle() == null || wish.getContent() == null || wish.getCategory() == null || wish.getCreatedAt() == null) {
            throw new IllegalArgumentException("필수 값이 누락되었습니다.");
        }
        return wishRepository.save(wish);
    }

    public void deleteWish(Long id) {
        Wish wish = wishRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("소원을 찾을 수 없습니다."));
        wish.setDeletedAt(LocalDateTime.now());
        wishRepository.save(wish);
    }

    public Wish approveWish(Long id) {
        Wish wish = wishRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("소원을 찾을 수 없습니다."));
        wish.setStatus(WishStatus.APPROVED);
        return wishRepository.save(wish);
    }

    public Wish rejectWish(Long id) {
        Wish wish = wishRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("소원을 찾을 수 없습니다."));
        wish.setStatus(WishStatus.REJECTED);
        return wishRepository.save(wish);
    }

    public List<Wish> getWishes(WishStatus status, Pageable pageable) {
        return wishRepository.findByDeletedAtIsNullAndStatus(status, pageable);
    }

    public List<Wish> searchWishes(String keyword, WishCategory category, Pageable pageable) {
        return wishRepository.findByTitleContainingOrContentContainingAndCategory(keyword, keyword, category);
    }
}
