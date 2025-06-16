package com.example.ch3codeyourself.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostPageResponse {
    private int page;
    private int size;
    private int totalCount;
    private int totalPages;
    private List<PostResponse> posts;

    public static PostPageResponse from(List<PostResponse> posts, PostSearchRequest search, int count) {
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
