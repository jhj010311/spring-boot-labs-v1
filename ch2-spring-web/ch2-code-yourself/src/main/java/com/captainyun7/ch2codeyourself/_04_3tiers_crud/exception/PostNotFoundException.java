package com.captainyun7.ch2codeyourself._04_3tiers_crud.exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(Long id) {
        super("id가 " + id + "인 게시글을 찾을 수 없습니다");
    }
}
