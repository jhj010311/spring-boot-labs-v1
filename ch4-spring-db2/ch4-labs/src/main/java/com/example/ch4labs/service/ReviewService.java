package com.example.ch4labs.service;

import com.example.ch4labs.domain.Review;
import com.example.ch4labs.dto.*;
import com.example.ch4labs.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    /*
    @Transactional(readOnly = true)
    public ReviewPageResponse search(ReviewSearchRequest search) {
        Pageable pageable = PageRequest.of(search.getPage(), search.getSize());
        Page<Review> result = null;

        if(search.getBookTitle() != null && !search.getBookTitle().isBlank()
                && search.getAuthor() != null && !search.getAuthor().isBlank()
                && search.getMinRating() != null && search.getMaxRating() != null) {

            result = repo.findByBookTitleContainingAndAuthorAndRatingGreaterThanEqualAndRatingLessThanEqual(
                    search.getBookTitle(), search.getAuthor(), search.getMinRating(), search.getMaxRating(), pageable);

        } else if(search.getBookTitle() != null && !search.getBookTitle().isBlank()) {

            result = repo.findByBookTitleContaining(search.getBookTitle(), pageable);

        } else if (search.getAuthor() != null && !search.getAuthor().isBlank()
                && search.getRating() != null) {

            result = repo.findByAuthorAndRating(search.getAuthor(), search.getRating(), pageable);

        } else if (search.getMinRating() != null && search.getMaxRating() != null) {

            result = repo.findByRatingGreaterThanEqualAndRatingLessThanEqual(search.getMinRating(), search.getMaxRating(), pageable);

        } else {
            result = repo.findAll(pageable);
        }

        Page<ReviewResponse> reviews = result.map(ReviewResponse::from);

        return ReviewPageResponse.from(reviews.getContent(), search, reviews.getTotalElements());
    }

     */

    @Transactional(readOnly = true)
    public ReviewPageResponse search(ReviewSearchRequest search) {
        Pageable pageable = PageRequest.of(search.getPage(), search.getSize());

        Page<ReviewResponse> result = repo.search(search);



        return ReviewPageResponse.from(result.getContent(), search, result.getTotalElements());
    }
}
