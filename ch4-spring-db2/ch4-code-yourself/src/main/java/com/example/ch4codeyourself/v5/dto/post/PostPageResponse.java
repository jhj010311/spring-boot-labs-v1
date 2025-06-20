package com.example.ch4codeyourself.v5.dto.post;

import com.example.ch4codeyourself.v5.dto.post.PostResponse;
import com.example.ch4codeyourself.v5.dto.post.PostSearchRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PostPageResponse {
    private int page;
    private int size;
    private long totalCount;
    private int totalPages;
    private List<com.example.ch4codeyourself.v5.dto.post.PostResponse> posts;

    public static PostPageResponse from(List<PostResponse> posts, PostSearchRequest search, Long count) {
        int totalPages = (int) Math.ceil((double) count / search.getSize());
        return new PostPageResponse(
                search.getPage(),
                search.getSize(),
                count,
                totalPages,
                posts
        );
    }
}
