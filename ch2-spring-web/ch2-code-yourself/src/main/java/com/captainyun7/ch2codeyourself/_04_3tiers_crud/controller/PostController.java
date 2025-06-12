package com.captainyun7.ch2codeyourself._04_3tiers_crud.controller;

import com.captainyun7.ch2codeyourself._04_3tiers_crud.dto.PostResponse;
import com.captainyun7.ch2codeyourself._04_3tiers_crud.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts") // api 버전 관리를 위해 이런 식으로 링크를 작성하기도 함
@RequiredArgsConstructor
public class PostController {

    /*

    // 필드 삽입은 권장되지 않습니다
    @Autowired
    PostService postService;

    */

    /*

    private final PostService postService;

    public PostController() {
        this.postService = new PostService();
    }
    
    // 생성자로 자동삽입
    // Autowired 생략 가능


     */

    private final PostService postService;
    // @RequiredArgsConstructor를 통해 생성자로 자동삽입
    // final로 처리된, 생성시 반드시 초기화되어야 하는 필드들만 자동삽입해준다
    // @AllArgsConstructor도 autowire는 해준다

    // 1. 클라이언트가 요청을 한다
    // 2. 컨트롤러가 서비스에게 일을 시킨다
    /*
    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id) {

        // DataInit에서 서버 시작과 동시에 가데이터 3개를 만든 상태
        // id 1~3은 추가 조치 없이 검색 가능

        return postService.getPost(id);
    }

    @GetMapping("")
    public List<Post> getAllPosts() {

        return postService.getAllPosts();
    }

    ResponseEntity + DTO 사용방식으로 리펙터링
     */

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {

        // DataInit에서 서버 시작과 동시에 가데이터 3개를 만든 상태
        // id 1~3은 추가 조치 없이 검색 가능

        return ResponseEntity.ok(postService.getPost(id));
    }

    @GetMapping("")
    public ResponseEntity<List<PostResponse>> getAllPosts() {

        return ResponseEntity.ok(postService.getAllPosts());
    }
}
