package com.captainyun7.ch2codeyourself._04_3tiers_crud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateRequest {
    private String title;
    private String body;
}
