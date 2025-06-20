package com.example.ch4codeyourself.v5.dto.comment;

import com.example.ch4codeyourself.v5.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponse {

    private Long id;
    private Long parentId;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private Long postId;
    private List<CommentResponse> replies = new ArrayList<>();

    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .parentId(comment.getParent().getId() != null ? comment.getParent().getId() : null) // 대댓글 관련 처리
                .content(comment.getContent())
                .author(comment.getAuthor())
                .createdAt(comment.getCreatedAt())
                .postId(comment.getPost().getId())
                .build();
    }
}
