package com.example.ch4codeyourself.v1.service;

import com.example.ch4codeyourself.v1.domain.Post;
import com.example.ch4codeyourself.v1.dto.PostCreateRequest;
import com.example.ch4codeyourself.v1.dto.PostResponse;
import com.example.ch4codeyourself.v1.dto.PostUpdateRequest;
import com.example.ch4codeyourself.v1.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

// Junit 플랫폼 : 테스트 코드 실행 주체, 테스트 코드 전체 흐름 담당
// Mockito : Mock 객체의 Mocking 기능을 담당
@ExtendWith(MockitoExtension.class) // >> 이후 실행시 Mockito를 쓸 것이라고 선언
class PostServiceTest {
    // 테스트할 클래스에서 alt + insert로 테스트할 메서드를 선택하여 생성 가능

    
    // 테스트를 위해 필요한 객체
    // PostService
    // PostRepository
    // 스프링 프레임워크는 필요 없음 >> 그런데 스프링 프레임워크가 없으면 bean이 없음
    // >> 어떤 방식이든 Autowired 불가



    /***//***//***//***//***//*중요*//***//***//***//***//***//***/
    
    @Mock   // "이 요소는 잘 될 것이다" 라고 가정하는 가짜 객체 / Mockito 필요
    private PostRepository repository;

    @InjectMocks    // 이후 필요한 자리에 Mock 객체를 주입
    private PostService service;

    /***//***//***//***//***//*중요*//***//***//***//***//***//***/


    
    
    @Test
    void createPost() {
        // PostCreateRequest 객체 필요
        // repository에 의존하는 save 작업 필요 >> 단 Mock을 통해 "잘 되었다"라고 가정
        // 최종적으로 return이 잘 되는지 확인 >> PostResponse 리턴값이 나오는지 검증
        
        
        // Given - When - Then 3단구조로 테스트
        
        // Given(조건)
        // PostCreateRequest 객체가 조건 > 원래는 Controller가 넘겨줬음
        PostCreateRequest request = new PostCreateRequest("테스트 게시글", "test body");

        // postRepository.save(post)의 리턴값을 지정
        // Mock 객체는 가상의 객체이기 때문에 이런 값이 돌아올 것이라고 설정해서 넘겨주는 것
        // import static org.mockito.BDDMockito.given;
        given(repository.save(any(Post.class)))
                .willReturn(new Post(1L, "테스트 게시글", "test body"));
        
        /*
        public PostResponse createPost(PostCreateRequest request) {
            Post post = request.toDomain();
            Post saved = repo.save(post);
            return PostResponse.from(saved);
        }
        
        saved 추가
         */


        // When(테스트 대상 == 목적)
        PostResponse result = service.createPost(request);


        // Then(검증)
        // import static org.assertj.core.api.Assertions.assertThat;
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("테스트 게시글");
        assertThat(result.getBody()).isEqualTo("test body");
        
        
        // 메서드 좌측 화살표로 실행
    }

    @Test
    void 게시글_수정_성공() {
        // given
        Long id = 1L;
        PostUpdateRequest request = new PostUpdateRequest("수정 제목", "수정 내용");

        Post post = new Post(id, "기존 제목", "기존 내용");

        given(repository.findById(id)).willReturn(Optional.of(post));


        // when
        PostResponse result = service.updatePost(id, request);


        // then
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getTitle()).isEqualTo("수정 제목");
        assertThat(result.getBody()).isEqualTo("수정 내용");

        verify(repository).findById(id);
    }
}