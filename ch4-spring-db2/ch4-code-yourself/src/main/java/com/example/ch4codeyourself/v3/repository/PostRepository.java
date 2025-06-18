package com.example.ch4codeyourself.v3.repository;

import com.example.ch4codeyourself.v3.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, PostQueryRepository {
    Page<Post> findByTitleContaining(String keyword, Pageable pageable);

    // findBy[필드][조건]And|Or[필드][조건]정렬

    // 작성자 일치
    Page<Post> findByAuthor(String author, Pageable pageable);

    // 제목에 검색어 포함 + 작성자 일치
    Page<Post> findByTitleContainingAndAuthor(String keyword, String author, Pageable pageable);

    Page<Post> findByCreatedAtAfter(LocalDateTime createdAt, Pageable pageable);



    // JPQL 문법
    // select * from posts where created_at >= '2025-06-10T00:00'
    @Query("SELECT p FROM Post p WHERE p.createdAt >= :createdAt")
    Page<Post> searchByCreatedAtAfter(@Param("createdAt") LocalDateTime createdAt, Pageable pageable);

    @Query("select p From Post p WHERE p.title LIKE %:keyword% AND p.author LIKE :author")
    Page<Post> searchByTitleContainingAndAuthor(@Param("keyword")String keyword, @Param("author")String author, Pageable pageable);
}
