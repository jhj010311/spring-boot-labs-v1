package com.captainyun7.ch501sessionbasedplainlogin.controller;

import com.captainyun7.ch501sessionbasedplainlogin.dto.LoginRequest;
import com.captainyun7.ch501sessionbasedplainlogin.dto.SignUpRequest;
import com.captainyun7.ch501sessionbasedplainlogin.dto.UserResponse;
import com.captainyun7.ch501sessionbasedplainlogin.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody SignUpRequest signUpRequest) {
        // TODO
        // 회원가입
        UserResponse userResponse = authService.register(signUpRequest);


        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody LoginRequest loginRequest, HttpSession session) {
        // TODO
        // 세션을 통해서 로그인 관리

        return ResponseEntity.ok(authService.login(loginRequest, session));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        // TODO
        // 세션을 지우고 초기화
        authService.logout(session);

        return ResponseEntity.ok().build();
    }
} 