package com.example.ch2labs.labs08;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserve")
public class ReserveController {

    @PostMapping
    public ResponseEntity<String> signup(@RequestBody @Valid ReservationRequest request) {
        return ResponseEntity.ok("회원가입 성공");

        // request 받아올 때 @Valid를 추가
        // + SignupRequest 클래스에 검증 어노테이션을 추가

    }
}
