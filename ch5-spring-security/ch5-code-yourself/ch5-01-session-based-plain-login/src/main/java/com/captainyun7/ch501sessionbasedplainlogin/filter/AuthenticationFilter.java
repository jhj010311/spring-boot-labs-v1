package com.captainyun7.ch501sessionbasedplainlogin.filter;

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
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // TODO : 필터 로직 작성

        // 권한에 따른 url 접근 권한 인가

        // 유저가 어느 url로 접근하는지 체크
        // 강제형변환 필요
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getRequestURI();


        // 1) 로그인하지 않았을 때도 가능한 것들을 미리 풀어주기
        if(isPublicPath(path)) {
            // chain을 통해 작업
            chain.doFilter(request, response);
        }

        // 2) 권한이 필요한 url에 필요한 사용자 정보를 검증
        HttpSession session = httpRequest.getSession(false);    // 없다고 만들지 말라

        // 세션이 없거나 사용자 정보가 세션에 없는 경우 == 검증실패
        if(session == null || session.getAttribute(USER_SESSION_KEY) == null) {
            // 실패했다고 클라이언트에 응답
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType("application/json");

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "인증이 필요합니다");
            errorResponse.put("status", "error");

            httpResponse.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        }
        // 사실 원래 이건 대부분 스프링이 자체적으로 해주니 할 일은 별로 없음?
        
        // 최종적으로 return하는 느낌으로 해줘야 함
        chain.doFilter(request, response);
    }
    
    private boolean isPublicPath(String path) {
        return PUBLIC_PATHS.stream().anyMatch(path::startsWith);
    }
} 