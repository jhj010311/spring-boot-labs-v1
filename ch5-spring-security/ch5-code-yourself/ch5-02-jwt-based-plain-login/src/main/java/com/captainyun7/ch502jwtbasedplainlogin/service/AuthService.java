package com.captainyun7.ch502jwtbasedplainlogin.service;

import com.captainyun7.ch502jwtbasedplainlogin.config.JwtUtil;
import com.captainyun7.ch502jwtbasedplainlogin.domain.User;
import com.captainyun7.ch502jwtbasedplainlogin.dto.LoginRequest;
import com.captainyun7.ch502jwtbasedplainlogin.dto.LoginResponse;
import com.captainyun7.ch502jwtbasedplainlogin.dto.SignUpRequest;
import com.captainyun7.ch502jwtbasedplainlogin.dto.UserResponse;
import com.captainyun7.ch502jwtbasedplainlogin.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserService userService;
//    private static final String USER_SESSION_KEY = "CURRENT_USER";
    private final JwtUtil jwtUtil;

    public UserResponse register(SignUpRequest signUpRequest) {
        // TODO

        // 이미 있는 유저인지 검증 + 이메일 검증
        // 없으면 저장, 있으면 회원가입 X

        if (userService.existsByUsername(signUpRequest.getUsername())) {
            throw new RuntimeException("이미 사용 중인 사용자 이름입니다.");
        }

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("이미 사용 중인 이메일입니다.");
        }

        return userService.createUser(signUpRequest);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        // TODO

        User user = userService.getUserByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("사용자명을 다시 확인해주세요"));

        if(!user.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다");
        }

        // 기존 세션방식 - 쿠키로 클라이언트에게 전달

        // JWT 방식으로
        // 토근을 발행 -> 클라이언트에 전달


        String token = jwtUtil.generateToken(user);


        return LoginResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .token(token)
                .build();
    }

    public void logout() {
        // TODO

        // jwt 토큰은 로그아웃 개념이 좀 모호하다
    }

    public User getCurrentUser(HttpSession session) {
        return null;
    }
} 