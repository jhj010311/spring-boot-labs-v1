package com.captainyun7.ch2codeyourself._03_rest_controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController // rest api 데이터를 관리하는 컨트롤러
@RequestMapping("rest/v1") // 모든 http 메서드를 받는다
public class RestController01 {

    @GetMapping("hello")
    public String hello() {
        return "hello";  // 데이터(API)
    }

    @GetMapping("/posts/{postId}")  // 게시글 typicode 이용
    public Post post(@PathVariable int postId) {
        Post post = new Post("샘플 게시글", "샘플 내용입니다");

        post.setId(1L);

        return post;
    }
    // Post쪽에 @Getter를 쓰지 않으면 406에러(Not Acceptable) 발생

    @GetMapping("/posts")
    public List<Post> posts() {
        List<Post> posts = new ArrayList<Post>();

        posts.add(new Post("첫번째 게시글", "내용1"));
        posts.add(new Post("두번째 게시글", "내용2"));
        posts.add(new Post("세번째 게시글", "내용3"));

        return posts;
    }

    // POST 메서드 : http body 안에 내용을 담아 요청
    // { title : ... , body : ... }
    @PostMapping("/posts")
    public Post addPost(@RequestBody Post post) {
        // body 안에 담긴 데이터를 Post 형식으로 읽으라는 의미

        post.setId(1L);

        return post;
    }
}