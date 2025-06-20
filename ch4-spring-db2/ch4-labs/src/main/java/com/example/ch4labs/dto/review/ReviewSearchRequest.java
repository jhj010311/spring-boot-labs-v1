package com.example.ch4labs.dto.review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewSearchRequest {
    private String author;
    private String bookTitle;
    private String bookTitleContains;
    private String bookAuthor;
    private String titleContains;
    private String contentContains;
    private Integer rating;
    private Integer minRating;
    private Integer maxRating;
    private String sort;
    private int page = 0;
    private int size = 10;
}
