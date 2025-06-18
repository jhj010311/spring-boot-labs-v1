package com.example.ch4labs.service;

import com.example.ch4labs.domain.Review;
import com.example.ch4labs.dto.ReviewCreateRequest;
import com.example.ch4labs.dto.ReviewResponse;
import com.example.ch4labs.dto.ReviewUpdateRequest;
import com.example.ch4labs.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository repo;

    public ReviewResponse createPost(ReviewCreateRequest request) {
        Review post = request.toDomain();
        Review saved = repo.save(post);
        return ReviewResponse.from(saved);
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> getAllReviews() {
        return repo.findAll().stream()
                .map(ReviewResponse::from)
                .toList();
    }

    public ReviewResponse updatePost(Long id, ReviewUpdateRequest request) {
        Review review = repo.findById(id).orElseThrow(() -> new NoSuchElementException("리뷰글이 존재하지 않습니다."));
        review.setTitle(request.getTitle());
        review.setContent(request.getContent());
        review.setAuthor(request.getAuthor());
        review.setBookTitle(request.getBookTitle());
        review.setBookAuthor(request.getBookAuthor());
        review.setRating(request.getRating());

        return ReviewResponse.from(review);
    }

    public void deletePost(Long id) {
        repo.deleteById(id);
    }
}
