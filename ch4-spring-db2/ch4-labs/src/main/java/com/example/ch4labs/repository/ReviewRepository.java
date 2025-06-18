package com.example.ch4labs.repository;

import com.example.ch4labs.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByBookTitleContaining(String keyword, Pageable pageable);



    Page<Review> findByAuthorAndRatingGreaterThanEqualAndRatingLessThanEqual(String author, int minRating, int maxRating, Pageable pageable);

    Page<Review> findByBookTitleContainingAndAuthorAndRatingGreaterThanEqualAndRatingLessThanEqual(String bookTitle, String author, Integer minRating, Integer maxRating, Pageable pageable);

    Page<Review> findByRatingGreaterThanEqualAndRatingLessThanEqual(Integer minRating, Integer maxRating, Pageable pageable);

    Page<Review> findByAuthorAndRating(String author, Integer rating, Pageable pageable);
}
