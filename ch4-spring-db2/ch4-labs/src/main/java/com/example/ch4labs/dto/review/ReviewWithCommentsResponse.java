package com.example.ch4labs.dto.review;

import com.example.ch4labs.domain.Review;
import com.example.ch4labs.dto.comment.CommentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewWithCommentsResponse {
    private ReviewResponse review;
    private Page<CommentResponse> comments;

    public static ReviewWithCommentsResponse from(Review review, Page<CommentResponse> comments) {
        return ReviewWithCommentsResponse.builder()
                .review(ReviewResponse.from(review))
                .comments(comments)
                .build();
    }
}
