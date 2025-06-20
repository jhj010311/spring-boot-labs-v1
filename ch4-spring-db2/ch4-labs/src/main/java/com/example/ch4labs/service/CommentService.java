package com.example.ch4labs.service;

import com.example.ch4labs.domain.Comment;
import com.example.ch4labs.domain.Review;
import com.example.ch4labs.dto.comment.*;
import com.example.ch4labs.repository.CommentRepository;
import com.example.ch4labs.repository.ReviewRepository;
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
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository repo;
    private final ReviewRepository reviewRepository;

    public CommentResponse createComment(Long reviewId, CommentCreateRequest request) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new EntityNotFoundException("Review not Found"));

        Comment comment = Comment.builder().content(request.getContent()).author(request.getAuthor())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .review(review)
                .build();

        Comment saved = repo.save(comment);

        return CommentResponse.from(saved);
    }


    /*
    @Transactional(readOnly = true)
    public CommentPageResponse getCommentsByReview(Long reviewId, int page, int size) {
        return CommentPageResponse.from(
                repo.findByReviewId(reviewId, PageRequest.of(page, size)).map(CommentResponse::from)
        );
    }

    대댓글 기능 추가하면서 주석화
     */

    @Transactional(readOnly = true)
    public CommentPageResponse getCommentsByReview(Long reviewId, CommentSearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("createdAt").descending());

        // 플랫구조인지 아닌지에 따라 2가지 분기
        if(request.isHierarchical()) {
            // 계층구조로 보여주기

            return getHierarchicalCommentsByReview(reviewId, pageable);
        } else {
            return getFlatCommentsByReview(reviewId, pageable);
        }
    }

    public CommentResponse updateComment(Long id, CommentUpdateRequest request) {
        Comment comment = repo.findById(id).orElseThrow(() -> new NoSuchElementException("댓글이 존재하지 않습니다."));
        comment.setContent(request.getContent());
        comment.setUpdatedAt(LocalDateTime.now());

        return CommentResponse.from(comment);
    }

    public void deleteComment(Long id) {
        repo.deleteById(id);
    }



    // 대댓글 기능 추가
    private CommentPageResponse getHierarchicalCommentsByReview(Long postId, Pageable pageable) {

        Page<Comment> page = repo.findByReviewIdAndParentIdNull(postId, pageable);

        List<CommentResponse> completeComments = new ArrayList<>();

        for (Comment parentComment : page.getContent()) {
            List<Comment> childComments = repo.findByParentIdOrderByCreatedAtDesc(parentComment.getId());

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

    private CommentPageResponse getFlatCommentsByReview(Long reviewId, Pageable pageable) {
        Page<CommentResponse> page = repo.findByReviewId(reviewId, pageable)
                .map(CommentResponse::from);

        return CommentPageResponse.from(page);
    }
}
