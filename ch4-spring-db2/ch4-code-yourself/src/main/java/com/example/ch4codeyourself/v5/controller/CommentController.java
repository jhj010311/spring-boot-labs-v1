package com.example.ch4codeyourself.v5.controller;

import com.example.ch4codeyourself.v5.dto.comment.*;
import com.example.ch4codeyourself.v5.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // CRUD

    // 댓글 생성
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponse> createComment(
            @PathVariable Long postId,
            @RequestBody CommentCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentService.createComment(postId, request));
    }

    /*
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentPageResponse> getComments(
            @PathVariable Long postId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size )
    {
        return ResponseEntity.ok(commentService.getCommentsByPost(postId, page, size));
    }

    250620 주석화
     */

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentPageResponse> getComments(
            @PathVariable Long postId,
            @ModelAttribute CommentSearchRequest request)
    {
        return ResponseEntity.ok(commentService.getCommentsByPost(postId, request));
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentUpdateRequest request) {
        CommentResponse response = commentService.updateComment(commentId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
