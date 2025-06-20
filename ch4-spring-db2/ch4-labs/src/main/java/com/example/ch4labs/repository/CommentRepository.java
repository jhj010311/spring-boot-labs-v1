package com.example.ch4labs.repository;

import com.example.ch4labs.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByReviewId(long id, Pageable pageable);

    Page<Comment> findByReviewIdAndParentIdNull(Long postId, Pageable pageable);

    List<Comment> findByParentIdOrderByCreatedAtDesc(Long id);
}
