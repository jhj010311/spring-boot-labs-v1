package com.example.ch4labs.controller;

import com.example.ch4labs.dto.review.*;
import com.example.ch4labs.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewsController {

    private final ReviewService service;

    @PostMapping
    public ResponseEntity<ReviewResponse> create(@RequestBody ReviewCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createReview(request));
    }

    @GetMapping
    /*public ResponseEntity<List<ReviewResponse>> getAll() {
        return ResponseEntity.ok(service.getAllReviews());
    }*/
    public ResponseEntity<ReviewPageResponse> searchReviews(ReviewSearchRequest request) {
        return ResponseEntity.ok(service.search(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponse> update(@PathVariable Long id, @RequestBody ReviewUpdateRequest request) {
        return ResponseEntity.ok(service.updateReview(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deletePost(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<ReviewWithCommentsResponse> getOneWithComments(@PathVariable Long id) {
        return ResponseEntity.ok(service.getReviewById(id));
    }
}
