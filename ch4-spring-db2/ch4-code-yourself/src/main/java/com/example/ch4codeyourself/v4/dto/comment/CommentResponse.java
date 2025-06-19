package com.example.ch4codeyourself.v4.dto.comment;

import com.example.ch4codeyourself.v4.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder    // Builder 어노테이션 - QueryDSL의 빌더 느낌으로 사용 가능
public class CommentResponse {

    private Long id;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private Long postId;

    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .author(comment.getAuthor())
                .createdAt(comment.getCreatedAt())
                .postId(comment.getPost().getId())
                .build();
    }
}
