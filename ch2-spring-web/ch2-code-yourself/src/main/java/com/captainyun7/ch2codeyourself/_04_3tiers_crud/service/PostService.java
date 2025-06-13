package com.captainyun7.ch2codeyourself._04_3tiers_crud.service;

import com.captainyun7.ch2codeyourself._04_3tiers_crud.domain.Post;
import com.captainyun7.ch2codeyourself._04_3tiers_crud.dto.PostCreateRequest;
import com.captainyun7.ch2codeyourself._04_3tiers_crud.dto.PostResponse;
import com.captainyun7.ch2codeyourself._04_3tiers_crud.dto.PostUpdateRequest;
import com.captainyun7.ch2codeyourself._04_3tiers_crud.exception.PostNotFoundException;
import com.captainyun7.ch2codeyourself._04_3tiers_crud.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// @Component 어노테이션의 시멘틱 변형(기능은 거의 같으나 이름이 다르다)
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;


    // DTO 사용 필요
    public PostResponse getPost(Long id) {
        /*
        Post post = repository.findById(id).orElseThrow(() -> new RuntimeException("Post Not Found"));
        // Optional 객체의 강점인 예외처리
        
         */

        Post post = repository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        // Optional 객체의 강점인 예외처리 ++ 커스텀 예외를 추가

        return toResponse(post);
    }

    public List<PostResponse> getAllPosts() {
        return repository.findAll().stream().map(post -> toResponse(post)).collect(Collectors.toList());
    }


    public PostResponse toResponse(Post post) {
        return new PostResponse(post.getId(), post.getTitle(), post.getBody());
    }

    public PostResponse createPost(PostCreateRequest request) {
        // dto를 도메인으로 변환
        Post savedPost = repository.save(new Post(null, request.getTitle(), request.getBody()));

        return toResponse(savedPost);
    }

    public void deletePost(Long id) {
        repository.delete(id);
        // 삭제가 안 됐을 경우의 예외처리 등을 추가하면 더 좋음
    }

    public PostResponse updatePost(Long id, PostUpdateRequest request) {
        // 1. id를 통해 수정 대상인 게시글을 가져온다
        // 2. 가져왔다면 request의 값으로 수정한다
        
        Post target = repository.findById(id).orElseThrow(() -> new RuntimeException("Post Not Found"));
        
        target.setTitle(request.getTitle());
        target.setBody(request.getBody());
        // 참조타입의 얕은 복사이므로 따로 명령어를 쓰지 않아도 원본이 변화한다

        return toResponse(target);
    }
}
