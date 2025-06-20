package com.example.ch4codeyourself.v5.repository;

import com.example.ch4codeyourself.v3.domain.Post;
import com.example.ch4codeyourself.v5.repository.PostQueryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<com.example.ch4codeyourself.v5.domain.Post, Long>, PostQueryRepository {

    // findBy[필드][조건]And|OR[필드][조건]정렬

    // 자동 생성 메서드 (= JPA가 메서드명만 보고 자동으로 만들어줌)
    Page<com.example.ch4codeyourself.v5.domain.Post> findByTitleContaining(String keyword, Pageable pageable);
    List<com.example.ch4codeyourself.v5.domain.Post> findByTitleContaining(String keyword);

    // 작성자 일치
    Page<com.example.ch4codeyourself.v5.domain.Post> findByAuthor(String author, Pageable pageable);

    // 제목에 검색어 포함 + 작성자 일치
    Page<com.example.ch4codeyourself.v5.domain.Post> findByTitleContainingAndAuthor(String keyword, String author, Pageable pageable);

    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword% AND p.author LIKE :author")
    Page<com.example.ch4codeyourself.v5.domain.Post> searchByTitleContainingAndAuthor(@Param("keyword") String keyword,
                                                                                      @Param("author") String author,
                                                                                      Pageable pageable);

    // 작성일자 이후에 대한 조회
    Page<com.example.ch4codeyourself.v5.domain.Post> findByCreatedAtAfter(LocalDateTime createdAt, Pageable pageable);

    // JPQL

    // SELECT * FROM posts WHERE created_at <= '2025-06-10T00:00'
    @Query("SELECT p FROM Post p WHERE p.createdAt >= :createdAt  ")
    Page<com.example.ch4codeyourself.v5.domain.Post> searchByAuthorAndTitle(@Param("createdAt") LocalDateTime createdAt, Pageable pageable);


}
