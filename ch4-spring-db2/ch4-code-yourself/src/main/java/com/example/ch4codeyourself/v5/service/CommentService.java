package com.example.ch4codeyourself.v5.service;

import com.example.ch4codeyourself.v5.domain.Comment;
import com.example.ch4codeyourself.v5.domain.Post;
import com.example.ch4codeyourself.v5.dto.comment.*;
import com.example.ch4codeyourself.v5.repository.CommentRepository;
import com.example.ch4codeyourself.v5.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentResponse createComment(Long postId, CommentCreateRequest request) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        Comment comment = null;

        if(request.getParentId() != null) {
            Comment parent = commentRepository.findById(request.getParentId()).orElseThrow(() -> new EntityNotFoundException("Comment not found"));

            comment = Comment.builder()
                    .content(request.getContent())
                    .parent(parent)
                    .author(request.getAuthor())
                    .createdAt(LocalDateTime.now())
                    .post(post)
                    .build();
        } else {
            comment = Comment.builder()
                    .content(request.getContent())
                    .author(request.getAuthor())
                    .createdAt(LocalDateTime.now())
                    .post(post)
                    .build();
        }

        Comment saved = commentRepository.save(comment);

        return CommentResponse.from(saved);
    }

    /*
    public CommentPageResponse getCommentsByPost(Long postId, int page, int size) {
        Page<Comment> comments = commentRepository.findByPostId(postId, PageRequest.of(page, size));
        return CommentPageResponse.from(comments.map(CommentResponse::from));
    }

    250620 2시 주석화
     */

    public CommentPageResponse getCommentsByPost(Long postId, CommentSearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("createdAt").descending());

        // 플랫구조인지 아닌지에 따라 2가지 분기
        if(request.isHierarchical()) {
            // 계층구조로 보여주기

            return getHierarchicalCommentsByPost(postId, pageable);
        } else {
            return getFlatCommentsByPost(postId, pageable);
        }
    }


    public CommentResponse updateComment(Long commentId, CommentUpdateRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("댓글이 존재하지 않습니다." + commentId));

        comment.setContent(request.getContent());

        return CommentResponse.from(comment);
    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));

        commentRepository.delete(comment);
    }



    // 250620 추가
    private CommentPageResponse getFlatCommentsByPost(Long postId, Pageable pageable) {
        Page<CommentResponse> page = commentRepository.findByPostId(postId, pageable)
                .map(CommentResponse::from);

        return CommentPageResponse.from(page);
    }

    private CommentPageResponse getHierarchicalCommentsByPost(Long postId, Pageable pageable) {
        // 계층형 응답

        // 부모댓글 + 부모댓글을 페이징
        // 부모댓글에 달린 자식댓글 계층형으로 덧붙여서 보여주기

        Page<Comment> page = commentRepository.findByPostIdAndParentIdNull(postId, pageable);

        List<CommentResponse> completeComments = new ArrayList<>();

        for (Comment parentComment : page.getContent()) {
            List<Comment> childComments = commentRepository.findByParentIdOrderByCreatedAtDesc(parentComment.getId());

            CommentResponse parentCommentResponse = CommentResponse.from(parentComment);
            parentCommentResponse.setReplies(
                    childComments.stream().map(CommentResponse::from).collect(Collectors.toList())
            );

            completeComments.add(parentCommentResponse);
        }

        return CommentPageResponse.builder()
                .page(page.getNumber()).size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .comments(completeComments)
                .build();
    }
}
