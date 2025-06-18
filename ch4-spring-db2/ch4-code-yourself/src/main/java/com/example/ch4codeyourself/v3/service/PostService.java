package com.example.ch4codeyourself.v3.service;

import com.example.ch4codeyourself.v3.domain.Post;
import com.example.ch4codeyourself.v3.dto.*;
import com.example.ch4codeyourself.v3.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository repo;

    public PostResponse createPost(PostCreateRequest request) {
        Post post = request.toDomain();
        Post saved = repo.save(post);
        return PostResponse.from(saved);
    }

    @Transactional(readOnly = true)
    public PostPageResponse getAllPosts(PostSearchRequest search) {
        Pageable pageable = PageRequest.of(search.getPage(), search.getSize());
        Page<Post> posts = null;

        // 조건식으로 분기
        // author, keyword, 기타 등등...

        if (!search.getKeyword().isBlank() && !search.getAuthor().isBlank()) {
            // posts = repo.findByTitleContainingAndAuthor(search.getKeyword(), search.getAuthor(), pageable);
            posts = repo.searchByTitleContainingAndAuthor(search.getKeyword(), search.getAuthor(), pageable);
        } else if(!search.getKeyword().isBlank()) {
            posts = repo.findByTitleContaining(search.getKeyword(), pageable);
        } else if (!search.getAuthor().isBlank()) {
            posts = repo.findByAuthor(search.getAuthor(), pageable);
        } else if (search.getCreatedAt() != null) {
            // posts = repo.findByCreatedAtAfter(search.getCreatedAt(), pageable);
            // posts = repo.searchByCreatedAtAfter(search.getCreatedAt(), pageable);
            posts = repo.searchByCreatedAtWithQueryDSL(search.getCreatedAt(), pageable);
        } else {
            posts = repo.findAll(pageable);
        }

        Page<PostResponse> page = posts.map(post -> PostResponse.from(post));

        return PostPageResponse.from(page.getContent(), search, posts.getTotalElements());

    }

    @Transactional(readOnly = true)
    public PostResponse getPostById(Long id) {
        return repo.findById(id)
                .map(PostResponse::from)
                .orElseThrow(() -> new NoSuchElementException("게시글이 존재하지 않습니다."));
    }

    public PostResponse updatePost(Long id, PostUpdateRequest request) {
        Post post = repo.findById(id).orElseThrow(() -> new NoSuchElementException("게시글이 존재하지 않습니다."));
        post.setTitle(request.getTitle());
        post.setBody(request.getBody());

        return PostResponse.from(post);
    }

    public void deletePost(Long id) {
        repo.deleteById(id);
    }
}