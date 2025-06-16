package com.example.ch3codeyourself.dto;

import com.example.ch3codeyourself.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String body;

    // Post -> PostResponse 변환
    public static PostResponse from(Post post) {
        return new PostResponse(post.getId(), post.getTitle(), post.getBody());
    }
}
