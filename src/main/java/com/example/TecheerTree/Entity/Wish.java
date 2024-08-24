package com.example.TecheerTree.Entity;

import com.example.TecheerTree.Entity.enums.WishCategory;
import com.example.TecheerTree.Entity.enums.WishStatus;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Wish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    @Enumerated(EnumType.STRING)
    private WishCategory category;

    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private WishStatus status = WishStatus.PENDING;

    private LocalDateTime deletedAt;

    // Constructors
    public Wish() {}

    public Wish(String title, String content, WishCategory category, LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
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

    public WishCategory getCategory() {
        return category;
    }

    public void setCategory(WishCategory category) {
        this.category = category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public WishStatus getStatus() {
        return status;
    }

    public void setStatus(WishStatus status) {
        this.status = status;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public boolean isDeleted() {
        return deletedAt != null;
    }
}
