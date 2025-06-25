package com.captainyun7.ch502jwtbasedplainlogin.controller;

import com.captainyun7.ch502jwtbasedplainlogin.dto.LoginRequest;
import com.captainyun7.ch502jwtbasedplainlogin.dto.LoginResponse;
import com.captainyun7.ch502jwtbasedplainlogin.dto.SignUpRequest;
import com.captainyun7.ch502jwtbasedplainlogin.dto.UserResponse;
import com.captainyun7.ch502jwtbasedplainlogin.service.AuthService;
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
        UserResponse userResponse = authService.register(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }


    /*
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody LoginRequest loginRequest, HttpSession session) {
        // TODO
        return ResponseEntity.ok(authService.login(loginRequest, session));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        // TODO
        authService.logout(session);
        return ResponseEntity.ok().build();
    }

    세션을 쓰던 구버전
     */

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        // TODO
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        // TODO
        authService.logout();
        return ResponseEntity.ok().build();
    }
} 