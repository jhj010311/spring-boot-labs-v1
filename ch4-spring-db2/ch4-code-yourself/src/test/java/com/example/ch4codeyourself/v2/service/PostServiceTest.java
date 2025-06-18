package com.example.ch4codeyourself.v2.service;

import com.example.ch4codeyourself.v2.domain.Post;
import com.example.ch4codeyourself.v2.dto.PostCreateRequest;
import com.example.ch4codeyourself.v2.dto.PostPageResponse;
import com.example.ch4codeyourself.v2.dto.PostResponse;
import com.example.ch4codeyourself.v2.dto.PostSearchRequest;
import com.example.ch4codeyourself.v2.repository.PostRepository;
import com.example.ch4codeyourself.v2.service.PostService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


// 통합테스트
// v1 때와 다르게 실제로 db에 연결할 수 있는 듯한? 환경설정을 한다 >> 서비스 + 레포지토리 형태로 테스트
// h2 인메모리 DB 방식 (가볍고 빠른 테스트용)

@Disabled
// 통합테스트용 어노테이션
@SpringBootTest
@Transactional // 여러 테스트를 돌릴 때 데이터 꼬임 방지로 롤백
class PostServiceTest {
    
    // 이번은 실제에 가깝게 쓰는 것이니 autowire 가능
    @Autowired
    private PostService service;
    
    @Autowired
    private PostRepository repo;


    @Test
    void 게시글_생성_조회_성공() {
        // given
        PostCreateRequest postCreateRequest = new PostCreateRequest("테스트 제목", "테스트 내용");

        // when
        PostResponse saved = service.createPost(postCreateRequest);
        PostResponse found = service.getPostById(saved.getId());

        // then
        assertThat(found.getTitle()).isEqualTo("테스트 제목");
        assertThat(found.getBody()).isEqualTo("테스트 내용");
    }


    @Test
    void 게시글_검색_페이징() {
        // 검색하려면 먼저 데이터가 필요하다
        // resource 폴더에 data.sql로 가데이터를 넣어도 좋고
        // 메서드마다 생성해도 좋고

        // given
        for (int i = 1; i <= 20; i++) {
            repo.save(new Post(null, "게시글" + i, "내용"));
        }

        for (int i = 1; i <= 20; i++) {
            repo.save(new Post(null, "hello" + i, "내용"));
        }

        // pageable에선 기본 페이지 값이 0
        PostSearchRequest request = new PostSearchRequest("게시글", 0, 10);


        // when
        PostPageResponse response = service.getAllPosts(request);

        // then
        assertThat(response.getPage()).isEqualTo(0);
        assertThat(response.getSize()).isEqualTo(10);
        assertThat(response.getTotalCount()).isEqualTo(2);
        assertThat(response.getPosts()).hasSize(10);
    }

}