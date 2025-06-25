package com.captainyun7.ch501sessionbasedplainlogin.service;

import com.captainyun7.ch501sessionbasedplainlogin.dto.LoginRequest;
import com.captainyun7.ch501sessionbasedplainlogin.dto.SignUpRequest;
import com.captainyun7.ch501sessionbasedplainlogin.dto.UserResponse;
import com.captainyun7.ch501sessionbasedplainlogin.domain.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserService userService;
    private static final String USER_SESSION_KEY = "CURRENT_USER";

    public UserResponse register(SignUpRequest signUpRequest) {
        // TODO
        // 회원가입

        // 리퀘스트를 받는다
        // 이미 있는 유저인지, 이메일이 중복되지 않는지 등 리퀘스트의 정보로 검증한다
        // 검증을 모두 통과하면 저장한다

        if(userService.existsByUsername(signUpRequest.getUsername())) {
            throw new RuntimeException("이미 있는 사용자 이름입니다");
        }

        if(userService.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("이미 있는 이메일입니다");
        }

        return userService.createUser(signUpRequest);
    }

    public UserResponse login(LoginRequest loginRequest, HttpSession session) {
        // TODO
        // 로그인의 로직

        // 사용자가 있는지 확인
        // 있는 경우 비밀번호 검증
        // 다 통과하면 세션에 사용자 정보 저장

        User user = userService.getUserByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("사용자명을 다시 확인해주세요"));

        if(!user.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다");
        }

        session.setAttribute(USER_SESSION_KEY, user);

        return UserResponse.fromEntity(user);
    }

    public void logout(HttpSession session) {
        // TODO
        // 세션에서 사용자 정보 삭제

        session.removeAttribute(USER_SESSION_KEY);
        session.invalidate();
    }

    public User getCurrentUser(HttpSession session) {
        return (User) session.getAttribute(USER_SESSION_KEY);
    }
} 