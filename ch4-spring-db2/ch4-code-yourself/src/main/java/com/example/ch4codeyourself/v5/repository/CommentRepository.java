package com.example.ch4codeyourself.v5.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.example.ch4codeyourself.v5.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByPostId(Long postId, Pageable pageable);

    Page<Comment> findByPostIdAndParentIdNull(Long postId, Pageable pageable);

    List<Comment> findByParentIdOrderByCreatedAtDesc(Long parentId);
}
