package com.captainyun7.ch2codeyourself._04_3tiers_crud.service;

import com.captainyun7.ch2codeyourself._04_3tiers_crud.domain.Post;
import com.captainyun7.ch2codeyourself._04_3tiers_crud.dto.PostResponse;
import com.captainyun7.ch2codeyourself._04_3tiers_crud.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// @Component 어노테이션의 시멘틱 변형(기능은 거의 같으나 이름이 다르다)
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


    // DTO 사용 필요
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post Not Found"));
        // Optional 객체의 강점인 예외처리

        return toResponse(post);
    }

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream().map(post -> toResponse(post)).collect(Collectors.toList());
    }


    public PostResponse toResponse(Post post) {
        return new PostResponse(post.getId(), post.getTitle(), post.getBody());
    }
}
