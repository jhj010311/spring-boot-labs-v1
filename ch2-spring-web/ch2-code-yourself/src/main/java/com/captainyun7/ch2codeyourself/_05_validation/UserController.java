package com.captainyun7.ch2codeyourself._05_validation;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validation")
public class UserController {

    /*
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request) {
        return ResponseEntity.ok("회원가입 성공");
        
        // 검증이 아예 되지 않는 서비스
        // 무검증으로 ok만 보낸다

        // if문 방식으로 할 경우
        // 코드가 필드 숫자에 비례해서, 필드의 검증조건 난이도에 따라서
        // 엄청나게 길어진다
    }
    
     */

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid SignupRequest request) {
        return ResponseEntity.ok("회원가입 성공");

        // request 받아올 때 @Valid를 추가
        // + SignupRequest 클래스에 검증 어노테이션을 추가
        
    }
}
