package com.example.ch4labs.dto.comment;

import com.example.ch4labs.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {
    private Long id;
    private String content;
    private String author;
    private Long reviewId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long parentId;
    private List<CommentResponse> replies = new ArrayList<>();

    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .author(comment.getAuthor())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .reviewId(comment.getReview().getId())
                .parentId(comment.getParent().getId() != null ? comment.getParent().getId() : null)
                .build();
    }
}
