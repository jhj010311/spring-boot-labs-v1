package com.example.ch3codeyourself.controller;

import com.example.ch3codeyourself.dto.*;
import com.example.ch3codeyourself.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService service;

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody PostCreateRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.createPost(request));
    }

    /*
    @GetMapping
    public ResponseEntity<List<PostResponse>> getPosts() {

        return ResponseEntity.ok(service.getPosts());
    }

    검색기능 없음
     */

    /*
    @GetMapping
    public ResponseEntity<List<PostResponse>> searchPosts(PostSearchRequest search) {
        // dto로 받는다고 선언하면 스프링이 알아서 연결해준다
        // requestBody는 생략 불가능, request param은 생략 가능

        return ResponseEntity.ok(service.searchPosts(search));
    }

    PostPageResponse 없을 때 만든 것
     */

    @GetMapping
    public ResponseEntity<PostPageResponse> searchPosts(PostSearchRequest search) {

        return ResponseEntity.ok(service.searchPosts(search));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long id) {

        return ResponseEntity.ok(service.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editPost(@PathVariable Long id, @RequestBody PostUpdateRequest request) {
        service.editPost(id, request);

        return ResponseEntity.ok("수정 완료");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        service.deletePost(id);

        return ResponseEntity.noContent().build();
    }
}
