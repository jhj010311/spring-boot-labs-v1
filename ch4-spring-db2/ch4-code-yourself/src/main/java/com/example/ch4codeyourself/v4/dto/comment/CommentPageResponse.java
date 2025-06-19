package com.example.ch4codeyourself.v4.dto.comment;

import com.example.ch4codeyourself.v4.dto.post.PostPageResponse;
import com.example.ch4codeyourself.v4.dto.post.PostResponse;
import com.example.ch4codeyourself.v4.dto.post.PostSearchRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentPageResponse {
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private List<CommentResponse> comments;

    public static CommentPageResponse from(Page<CommentResponse> page) {
        return CommentPageResponse.builder()
                .page(page.getNumber()).size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .comments(page.getContent())
                .build();
    }
}
