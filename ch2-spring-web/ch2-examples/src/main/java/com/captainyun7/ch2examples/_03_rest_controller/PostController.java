package com.captainyun7.ch2examples._03_rest_controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/rest/v3")
public class PostController {

    private final HashMap<Long, Post> postStore = new HashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public PostController() {
        Post post1 = new Post("첫번째 게시글", "안녕하세요, 첫번째 게시글입니다.");
        Post post2 = new Post("두번째 게시글", "반갑습니다, 두번째 게시글입니다.");
        Post post3 = new Post("세번째 게시글", "또 만나요, 세번째 게시글입니다.");

        post1.setId(counter.getAndIncrement());
        post2.setId(counter.getAndIncrement());
        post3.setId(counter.getAndIncrement());

        postStore.put(post1.getId(), post1);
        postStore.put(post2.getId(), post2);
        postStore.put(post3.getId(), post3);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity.ok(new ArrayList<>(postStore.values()));
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        long id = counter.getAndIncrement();
        post.setId(id);
        postStore.put(id, post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postStore.get(id);
        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        if (postStore.containsKey(id)) {
            updatedPost.setId(id);
            postStore.put(id, updatedPost);
            return ResponseEntity.ok(updatedPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        if (postStore.containsKey(id)) {
            postStore.remove(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
