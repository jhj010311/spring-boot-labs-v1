package com.example.ch4codeyourself.v3.repository;

import com.example.ch4codeyourself.v3.dto.PostResponse;
import com.example.ch4codeyourself.v3.dto.PostSearchRequest;
import com.example.ch4codeyourself.v3.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface PostQueryRepository {

    // 작성일자 이후에 대한 조회
    Page<Post> searchByCreatedAtWithQueryDSL(LocalDateTime createdAt, Pageable pageable);

    // 서비스에서 가공할 필요 없도록 반환값 지정
    Page<PostResponse> search(PostSearchRequest request);
}
