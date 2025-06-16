package com.example.ch3codeyourself.dto;

import com.example.ch3codeyourself.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostUpdateRequest {
    private Long id;
    private String title;
    private String body;

    public Post toDomain() {
        Post post = new Post();

        post.setId(id);
        post.setTitle(title);
        post.setBody(body);

        return post;
    }
}
