package com.captainyun7.ch2codeyourself._06_external_api;

import lombok.Data;


// @getter, @setter 등 6가지를 포함한 어노테이션
// 사실 그렇게 권장되진 않음
@Data
public class Post {
    private Long id;
    private Long userId;
    private String title;
    private String body;
}
