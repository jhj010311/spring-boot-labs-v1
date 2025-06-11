package com.captainyun7.ch2examples._03_rest_controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/v2")
public class RestController02 {

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping("/post")
    public ResponseEntity<Post> getPost() {
        Post post = new Post("샘플 게시글", "RestController 예제입니다.");
        post.setId(0L);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getPosts() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("첫번째 게시글", "안녕하세요, 첫번째 게시글입니다."));
        posts.add(new Post("두번째 게시글", "안녕하세요, 두번째 게시글입니다."));
        posts.add(new Post("세번째 게시글", "안녕하세요, 세번째 게시글입니다."));
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/201")
    public ResponseEntity<String> created() {
        return ResponseEntity.status(HttpStatus.CREATED).body("created!");
    }

    @DeleteMapping("/204")
    public ResponseEntity<Void> noContent() {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/400")
    public ResponseEntity<String> badRequest() {
        return ResponseEntity.badRequest().body("잘못된 요청입니다.");
    }

    @GetMapping("/404")
    public ResponseEntity<String> notFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("데이터를 찾을 수 없습니다.");
    }

    @GetMapping("/500")
    public ResponseEntity<String> serverError() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버에 오류가 발생했습니다.");
    }



}
