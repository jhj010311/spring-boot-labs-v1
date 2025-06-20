package com.example.ch4labs.controller;

import com.example.ch4labs.dto.comment.*;
import com.example.ch4labs.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;

    @PostMapping("/reviews/{reviewId}/comments")
    public ResponseEntity<CommentResponse> createComment(@PathVariable Long reviewId, @RequestBody CommentCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createComment(reviewId, request));
    }

    @GetMapping("/reviews/{reviewId}/comments")
    public ResponseEntity<CommentPageResponse> getComments(@PathVariable Long reviewId,
                                                           @ModelAttribute CommentSearchRequest request) {
        return ResponseEntity.ok(service.getCommentsByReview(reviewId, request));
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long id, @RequestBody CommentUpdateRequest request) {
        return ResponseEntity.ok(service.updateComment(id, request));
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        service.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

}
