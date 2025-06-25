package com.captainyun7.ch505oauthspringsecuritylogin.controller;

import com.captainyun7.ch505oauthspringsecuritylogin.dto.*;
import com.captainyun7.ch505oauthspringsecuritylogin.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody SignUpRequest signUpRequest) {
        UserResponse userResponse = authService.register(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }
    
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.login(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }
    
    @PostMapping("/refreshtoken")
    public ResponseEntity<TokenRefreshResponse> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
        String newAccessToken = authService.refreshAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(new TokenRefreshResponse(newAccessToken, request.getRefreshToken()));
    }
    
    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        authService.logout();
        return ResponseEntity.ok().build();
    }
    
    /**
     * OAuth2 로그인 관련 정보를 제공하는 엔드포인트
     */
    @GetMapping("/oauth2-providers")
    public ResponseEntity<Map<String, String>> getOAuth2Providers() {
        Map<String, String> providers = new HashMap<>();
        providers.put("github", "/oauth2/authorize/github");
        return ResponseEntity.ok(providers);
    }
    
    /**
     * OAuth2 로그인 후 리다이렉트 처리를 위한 엔드포인트
     */
    @GetMapping("/oauth2/redirect")
    public ResponseEntity<JwtResponse> getOAuth2Tokens(@RequestParam String token, @RequestParam String refreshToken) {
        return ResponseEntity.ok(new JwtResponse(token, refreshToken));
    }
} 