package com.example.ch4codeyourself.v5.dto.post;

import com.example.ch4codeyourself.v5.domain.Post;
import com.example.ch4codeyourself.v5.dto.comment.CommentResponse;
import com.example.ch4codeyourself.v5.dto.post.PostResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostWithCommentsResponse {

    private com.example.ch4codeyourself.v5.dto.post.PostResponse post;
    private Page<CommentResponse> comments;

    public static PostWithCommentsResponse from(Post post, Page<CommentResponse> comments) {
        return PostWithCommentsResponse.builder()
                .post(PostResponse.from(post))
                .comments(
                    comments
                )
                .build();
    }
}
