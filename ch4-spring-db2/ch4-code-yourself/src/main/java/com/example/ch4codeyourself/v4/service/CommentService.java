package com.example.ch4codeyourself.v4.service;

import com.example.ch4codeyourself.v4.domain.Comment;
import com.example.ch4codeyourself.v4.domain.Post;
import com.example.ch4codeyourself.v4.dto.comment.CommentCreateRequest;
import com.example.ch4codeyourself.v4.dto.comment.CommentPageResponse;
import com.example.ch4codeyourself.v4.dto.comment.CommentResponse;
import com.example.ch4codeyourself.v4.dto.comment.CommentUpdateRequest;
import com.example.ch4codeyourself.v4.dto.post.PostResponse;
import com.example.ch4codeyourself.v4.repository.CommentRepository;
import com.example.ch4codeyourself.v4.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository repo;
    private final PostRepository postRepository;

    public CommentResponse createComment(Long postId, CommentCreateRequest request) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post not Found"));

        Comment comment = Comment.builder().content(request.getContent()).author(request.getAuthor())
                .createdAt(LocalDateTime.now())
                .post(post)
                .build();

        Comment saved = repo.save(comment);

        return CommentResponse.from(saved);
    }


    @Transactional(readOnly = true)
    public CommentPageResponse getCommentsByPost(Long postId, int page, int size) {
        return CommentPageResponse.from(
                repo.findByPostId(postId, PageRequest.of(page, size)).map(CommentResponse::from)
        );
    }

    public CommentResponse updateComment(Long id, CommentUpdateRequest request) {
        Comment comment = repo.findById(id).orElseThrow(() -> new NoSuchElementException("댓글이 존재하지 않습니다."));
        comment.setContent(request.getContent());

        return CommentResponse.from(comment);
    }

    public void deleteComment(Long id) {
        repo.deleteById(id);
    }
}
