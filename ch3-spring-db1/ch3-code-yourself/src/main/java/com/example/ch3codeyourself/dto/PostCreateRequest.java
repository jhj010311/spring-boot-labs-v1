package com.example.ch3codeyourself.dto;

import com.example.ch3codeyourself.domain.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostCreateRequest {
    private String title;
    private String body;

    public Post toDomain() {
        Post post = new Post();
        
        post.setTitle(title);
        post.setBody(body);
        
        return post;
    }
    
    // 리퀘스트 클래스 안에서 처리하면 응집도가 좋다
}
