package com.captainyun7.ch501sessionbasedplainlogin.interceptor;

import com.captainyun7.ch501sessionbasedplainlogin.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthorizationInterceptor implements HandlerInterceptor {
    
    private static final String USER_SESSION_KEY = "CURRENT_USER";
    private final ObjectMapper objectMapper;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // TODO : 인터셉터 로직 작성

        // 권한 검증을 위한 URI path
        String path = request.getRequestURI();

        if(path.startsWith("/api/admin")) {
            HttpSession session = request.getSession();

            if(session == null) {
                sendUnauthorizedResponse(response, "로그인이 필요합니다");
                return false;
            }

            User user = (User) session.getAttribute(USER_SESSION_KEY);

            if(user == null) {
                sendUnauthorizedResponse(response, "로그인이 필요합니다");
                return false;
            }

            if(!user.getRole().equals("ADMIN")) {
                sendUnauthorizedResponse(response, "관리자권한이 필요합니다");
                return false;
            }
        }

        return true;
    }
    
    private void sendUnauthorizedResponse(HttpServletResponse response, String message) throws Exception {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", message);
        errorResponse.put("status", "error");
        
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
    
    private void sendForbiddenResponse(HttpServletResponse response, String message) throws Exception {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", message);
        errorResponse.put("status", "error");
        
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
} 