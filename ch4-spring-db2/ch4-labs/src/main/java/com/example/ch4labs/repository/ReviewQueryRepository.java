package com.example.ch4labs.repository;

import com.example.ch4labs.dto.review.ReviewResponse;
import com.example.ch4labs.dto.review.ReviewSearchRequest;
import org.springframework.data.domain.Page;

public interface ReviewQueryRepository {

    Page<ReviewResponse> search(ReviewSearchRequest request);
}
