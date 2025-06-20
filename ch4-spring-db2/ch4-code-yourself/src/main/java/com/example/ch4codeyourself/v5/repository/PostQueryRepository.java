package com.example.ch4codeyourself.v5.repository;

import com.example.ch4codeyourself.v5.domain.Post;
import com.example.ch4codeyourself.v5.dto.post.PostResponse;
import com.example.ch4codeyourself.v5.dto.post.PostSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface PostQueryRepository {
    // 작성일자 이후에 대한 조회
    Page<Post> searchByCreatedAtWithQueryDSL(LocalDateTime createdAt, Pageable pageable);
    Page<PostResponse> search(PostSearchRequest request);
}
