package com.example.ch4codeyourself.v2.service;

import com.example.ch4codeyourself.v2.domain.Post;
import com.example.ch4codeyourself.v2.dto.*;
import com.example.ch4codeyourself.v2.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    // !1) mybatis에서 jpa로 바꾼다
    // private final PostMapper postMapper;
    private final PostRepository repo;

    public PostResponse createPost(PostCreateRequest request) {
        Post post = request.toDomain();
        Post saved = repo.save(post);
        return PostResponse.from(saved);
    }
    // save는 자체적으로 트랜잭션이 이루어진다
    // mybatis 때와는 다르게, saved 객체로 결과물을 받아서 넘겨주는 것이 좋다 

    @Transactional(readOnly = true)
    public PostPageResponse getAllPosts(PostSearchRequest search) {
        // import org.springframework.data.domain.Pageable;
        // Pageable : 페이징 설정 관리용 인터페이스
        Pageable pageable = PageRequest.of(search.getPage(), search.getSize());

        // 키워드가 제목에 포함된 게시글을 가져오는 것이 목적
        // 이를 쉽게 하는 jpa 기능 >> 자동생성 메서드
        /*
        return repo.findByTitleContaining(search.getKeyword(), pageable)
                .map(PostResponse::from);

         */

        Page<PostResponse> page = repo.findByTitleContaining(search.getKeyword(), pageable)
                .map(PostResponse::from);

        return PostPageResponse.from(page.getContent(), search, page.getTotalElements());

    }
    // !3) 클래스 레벨로 트랜잭션을 건 경우
    // 그냥 읽어오는 것에도 트랜잭션이 되면 성능 저하가 일어난다
    // @Transactional(readOnly = true) 를 통해 차단한다

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

        // 업데이트가 되었는지 안 되었는지 이곳에서 체크할 필요 없음
        // save할 필요도 없음
        // 영속성 컨텍스트, 더티체킹
        // setter를 통한 값의 변화를 알아서 감지해서 쿼리로 발송함
        // 따라서 jpa repository에선 update라는 구문 자체가 발생하지 않음

        // !2) 체킹은 자동으로 해줘도 트랜잭션까지 해야 실제로 db에 반영이 된다
        // 수정, 삭제는 그렇다
        // @Transactional
        // import org.springframework.transaction.annotation.Transactional;
        // 클래스 레벨로 걸 수도 있다

        return PostResponse.from(post);
    }

    public void deletePost(Long id) {
        repo.deleteById(id);
    }
}