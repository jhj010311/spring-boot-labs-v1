package com.captainyun7.ch502jwtbasedplainlogin.filter;

import com.captainyun7.ch502jwtbasedplainlogin.config.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Order(1)
@RequiredArgsConstructor
public class AuthenticationFilter implements Filter {
    
    private static final String USER_SESSION_KEY = "CURRENT_USER";
    private static final List<String> PUBLIC_PATHS = Arrays.asList(
            "/swagger-ui",
            "/v3/api-docs",
            "/swagger-resources/**",
            "/api/auth/login",
            "/api/auth/register",
            "/h2-console"
    );
    
    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // TODO : 필터 로직 작성

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getRequestURI();
        
        // 로그인하지 않았을 때도 공개 경로는 가능
        if (isPublicPath(path)) {
            // 다음 스텝
            chain.doFilter(request, response);
        }
        
//
//        HttpSession session = httpRequest.getSession(false);
//
//        // 사용자 정보를 검증
//        // 세션이 없거나 사용자 정보가 세션에 없으면 검증 실패
//        if (session == null || session.getAttribute(USER_SESSION_KEY) == null) {
//            // 인증 실패했다고 클라이언트에 응답
//            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            httpResponse.setContentType("application/json");
//            
//            Map<String, String> errorResponse = new HashMap<>();
//            errorResponse.put("error", "인증이 필요합니다");
//            errorResponse.put("status", "error");
//
//            httpResponse.getWriter().write(objectMapper.writeValueAsString(errorResponse));
//            return;
//        }
        
        
        // JWT 토큰 방식 인증
        // 클라이언트로부터 토큰이 함께 전송됨
        // 토큰이 있나 없나로 판단

        String bearerToken = httpRequest.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            bearerToken = bearerToken.substring(7);
        }

        if (bearerToken == null || !jwtUtil.validateToken(bearerToken)) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType("application/json");

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "인증이 필요합니다.");
            errorResponse.put("status", "error");

            httpResponse.getWriter().write(objectMapper.writeValueAsString(errorResponse));
            return;
        }



        chain.doFilter(request, response);
    }
    
    private boolean isPublicPath(String path) {
        return PUBLIC_PATHS.stream().anyMatch(path::startsWith);
    }
} 