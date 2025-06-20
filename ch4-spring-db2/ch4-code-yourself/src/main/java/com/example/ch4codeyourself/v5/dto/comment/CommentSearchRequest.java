package com.example.ch4codeyourself.v5.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentSearchRequest {
    
    @Builder.Default
    private int page = 0;

    @Builder.Default
    private int size = 10;

    @Builder.Default
    private boolean hierarchical = true;
    // true면 계층구조로 보여줄 것
    // false면 평면구조로 보여줄 것
}
