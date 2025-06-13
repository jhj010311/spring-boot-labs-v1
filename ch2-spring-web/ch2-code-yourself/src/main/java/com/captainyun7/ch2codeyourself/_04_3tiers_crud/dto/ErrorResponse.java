package com.captainyun7.ch2codeyourself._04_3tiers_crud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    private String code;
    private String message;
}
