package com.example.ch3codeyourself.dto;

import lombok.Data;

@Data
public class PostSearchRequest {
    // null을 주면 mybatis가 에러를 일으킬 소지가 있다
    private String keyword = "";
    private int page = 1;
    private int size = 10;
    
    // get*** 형태로 만들면 mybatis가 알아서 읽는다
    public int getOffset() {
        return (page - 1) * size;
    }
}
