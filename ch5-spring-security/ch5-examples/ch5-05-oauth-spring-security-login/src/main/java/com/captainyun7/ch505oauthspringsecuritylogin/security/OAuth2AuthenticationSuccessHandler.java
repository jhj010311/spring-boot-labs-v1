package com.captainyun7.ch505oauthspringsecuritylogin.security;

import com.captainyun7.ch505oauthspringsecuritylogin.domain.RefreshToken;
import com.captainyun7.ch505oauthspringsecuritylogin.domain.User;
import com.captainyun7.ch505oauthspringsecuritylogin.repository.UserRepository;
import com.captainyun7.ch505oauthspringsecuritylogin.service.RefreshTokenService;
import com.captainyun7.ch505oauthspringsecuritylogin.util.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, 
                                       Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2 로그인 성공 처리");
        
        DefaultOAuth2User oauthUser = (DefaultOAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oauthUser.getAttributes();
        
        String email = (String) attributes.getOrDefault("email", "");
        
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            log.error("인증된 사용자를 데이터베이스에서 찾을 수 없습니다: {}", email);
            getRedirectStrategy().sendRedirect(request, response, "/login?error=user_not_found");
            return;
        }
        
        User user = userOptional.get();
        
        // JWT 토큰 생성
        UserDetails userDetails = org.springframework.security.core.userdetails.User
            .withUsername(user.getUsername())
            .password(user.getPassword() != null ? user.getPassword() : "")
            .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole())))
            .build();

        String accessToken = jwtUtil.generateToken(userDetails);
        
        // 리프레시 토큰 생성
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getUsername());
        
        // 프론트엔드 리다이렉트 URL (예시)
        String targetUrl = UriComponentsBuilder.fromUriString("/oauth2/redirect")
            .queryParam("token", accessToken)
            .queryParam("refreshToken", refreshToken.getToken())
            .build().toUriString();
        
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
} 