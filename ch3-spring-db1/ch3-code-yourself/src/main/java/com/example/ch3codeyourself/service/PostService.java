package com.example.ch3codeyourself.service;

import com.example.ch3codeyourself.domain.Post;
import com.example.ch3codeyourself.dto.*;
import com.example.ch3codeyourself.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    // MyBatis mapper
    private final PostMapper mapper;
    private final PostMapper postMapper;

    public PostResponse createPost(PostCreateRequest request) {
        // 서비스 - 레포지토리 - db 사이에선 DTO보단 도메인으로 넘겨주기
        // PostCreateRequest를 Post로 서비스 단계에서 전환
        Post post = request.toDomain();

        mapper.save(post);

        return PostResponse.from(post);
    }

    public List<PostResponse> getPosts() {
        return mapper.findAll().stream()
                .map(post -> PostResponse.from(post)).toList();
    }

    public PostResponse getPostById(Long id) {
        return PostResponse.from(mapper.findOne(id));
    }

    public void editPost(Long id, PostUpdateRequest request) {
        Post post = request.toDomain();
        post.setId(id);

        mapper.edit(post);
    }

    public void deletePost(Long id) {
        mapper.delete(id);
    }

    /*
    public List<PostResponse> searchPosts(PostSearchRequest search) {

        return mapper.search(search).stream()
                .map(post -> PostResponse.from(post)).toList();
    }

    PostPageResponse 없을 때 만든 것
     */

    public PostPageResponse searchPosts(PostSearchRequest search) {
        List<PostResponse> posts = mapper.search(search).stream()
                .map(post -> PostResponse.from(post)).toList();

        int count = postMapper.count(search);

        return PostPageResponse.from(posts, search, count);
    }
}
