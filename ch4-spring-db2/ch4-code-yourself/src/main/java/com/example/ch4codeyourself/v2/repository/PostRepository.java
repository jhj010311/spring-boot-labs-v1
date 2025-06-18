package com.example.ch4codeyourself.v2.repository;

import com.example.ch4codeyourself.v2.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // JpaRepository 클래스

    // !1) CRUD 제작
    // jpa는 레포지토리에 있는 메서드를 자동으로 추상화한다(extends JpaRepository<Post, Long>를 통해)
    // 아무것도 안 써도 알아서 CRUD가 제작된다
    // 끝!
    
    // !v2-1) 예약어를 사용한 동적 쿼리
    // jpa는 자동생성 메서드를 제공
    // 메서드명만 보고 자동으로 만들어 줌
    Page<Post> findByTitleContaining(String keyword, Pageable pageable);
}
