package com.captainyun7.ch2codeyourself._03_rest_controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("rest/v2")
public class RestController02 {

    // ResponseEntity : 스프링의 HTTP응답 객체
    // http에는 헤더 + 바디 + 상태코드로 이루어져 있음
    // 이 3가지를 전부 묶어서 넘기고자 할 때 ResponseEntity를 사용

    @GetMapping("hello")
    public ResponseEntity<String> hello() {

        // return ResponseEntity.status(201).build(); 같은 식으로 응답 가능
        // 다양한 옵션을 추가할 수 있다
        return ResponseEntity.ok("hello");
    }

    @GetMapping("/posts/{postId}")  // 게시글 typicode 이용
    public ResponseEntity<Post> getPost(@PathVariable long postId) {
        Post post = new Post("샘플 게시글", "샘플 내용입니다");

        post.setId(1L);

        return ResponseEntity.ok(post);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getPosts() {
        List<Post> posts = new ArrayList<Post>();

        posts.add(new Post("첫번째 게시글", "내용1"));
        posts.add(new Post("두번째 게시글", "내용2"));
        posts.add(new Post("세번째 게시글", "내용3"));

        return ResponseEntity.ok(posts);
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> addPost(@RequestBody Post post) {
        // body 안에 담긴 데이터를 Post 형식으로 읽으라는 의미

        post.setId(1L);

        return ResponseEntity.ok(post);
    }




    // http 상태 코드
    // 200번대 : 성공
    // 300번대 : redirect, 진행중 등
    // 400번대 : 클라이언트 측에 책임이 있는 에러 >> 지적 내용이 많음
    // 500번대 : 서버 측에 책임이 있는 에러 >> 지적 내용 별로 알려주지 않음
    //
    // 상태코드 건드리기 예제들

    @PostMapping("/201")
    public ResponseEntity<String> post201() {
        // HttpStatus : enum 객체

        return ResponseEntity.status(HttpStatus.CREATED).body("201");
    }

    @DeleteMapping("/204")
    public ResponseEntity<String> delete204() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("204");
    }

    @GetMapping("/401")
    public ResponseEntity<String> get401() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("401");
    }

    @GetMapping("/403")
    public ResponseEntity<String> get403() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("403");
    }

    @GetMapping("/500")
    public ResponseEntity<String> get500() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500");
    }

    @GetMapping("/plain-text")
    public ResponseEntity<String> plainText() {
        return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body("plain text");
    }

    @PostMapping("/location")
    public ResponseEntity<String> location() {
        // redirect 등에 사용 가능
        URI location = URI.create("/rest/v2/posts");
        
        // 자동으로 헤더를 구성
        return ResponseEntity.status(HttpStatus.FOUND).location(location).build();
    }

    @GetMapping("/no-cache")
    public ResponseEntity<String> noCache() {
        // headerValues 안에는 여러 가지를 한 번에 넣을 수 있다
        return ResponseEntity.ok().header("Cache-Control", "no-cache, no-store, must-revalidate")
                .body("항상 최신 데이터를 받아오세요");
    }
}