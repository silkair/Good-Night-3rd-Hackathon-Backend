package com.example.TecheerTree.Controller;

import com.example.TecheerTree.Entity.Wish;
import com.example.TecheerTree.Entity.enums.WishCategory;
import com.example.TecheerTree.Entity.enums.WishStatus;
import com.example.TecheerTree.Service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishes")
public class WishController {

    @Autowired
    private WishService wishService;

    @PostMapping
    public ResponseEntity<Wish> createWish(@RequestBody Wish wish) {
        return new ResponseEntity<>(wishService.createWish(wish), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWish(@PathVariable Long id) {
        wishService.deleteWish(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<Wish> approveWish(@PathVariable Long id) {
        return new ResponseEntity<>(wishService.approveWish(id), HttpStatus.OK);
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<Wish> rejectWish(@PathVariable Long id) {
        return new ResponseEntity<>(wishService.rejectWish(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Wish>> getWishes(@RequestParam WishStatus status, Pageable pageable) {
        return new ResponseEntity<>(wishService.getWishes(status, pageable), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Wish>> searchWishes(@RequestParam String keyword, @RequestParam WishCategory category, Pageable pageable) {
        return new ResponseEntity<>(wishService.searchWishes(keyword, category, pageable), HttpStatus.OK);
    }
}