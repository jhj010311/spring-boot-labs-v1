package com.captainyun7.ch505oauthspringsecuritylogin.service;

import com.captainyun7.ch505oauthspringsecuritylogin.domain.User;
import com.captainyun7.ch505oauthspringsecuritylogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuth2UserServiceImpl extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        
        try {
            return processOAuth2User(userRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("OAuth2 인증 처리 중 예외 발생", ex);
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        GithubOAuth2UserInfo userInfo = new GithubOAuth2UserInfo(oAuth2User.getAttributes());
        
        if (!StringUtils.hasText(userInfo.getEmail())) {
            throw new OAuth2AuthenticationException("이메일을 찾을 수 없습니다");
        }

        Optional<User> userOptional = userRepository.findByEmail(userInfo.getEmail());
        User user;
        
        if (userOptional.isPresent()) {
            user = userOptional.get();
            if (!user.getProvider().equals("github")) {
                throw new OAuth2AuthenticationException("이미 다른 공급자로 가입한 이메일입니다: " + user.getProvider());
            }
            user = updateExistingUser(user, userInfo);
        } else {
            user = registerNewUser(userRequest, userInfo);
        }

        return new DefaultOAuth2User(
            Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole())),
            oAuth2User.getAttributes(),
            "id"
        );
    }

    private User registerNewUser(OAuth2UserRequest userRequest, GithubOAuth2UserInfo userInfo) {
        User user = User.builder()
                .provider("github")
                .providerId(userInfo.getId())
                .name(userInfo.getName())
                .email(userInfo.getEmail())
                .username(userInfo.getEmail()) // GitHub 이메일을 사용자 이름으로 설정
                .imageUrl(userInfo.getImageUrl())
                .role("USER")
                .build();
        
        return userRepository.save(user);
    }

    private User updateExistingUser(User existingUser, GithubOAuth2UserInfo userInfo) {
        existingUser.setName(userInfo.getName());
        existingUser.setImageUrl(userInfo.getImageUrl());
        return userRepository.save(existingUser);
    }

    // GitHub 사용자 정보를 매핑하는 내부 클래스
    private static class GithubOAuth2UserInfo {
        private final Map<String, Object> attributes;

        public GithubOAuth2UserInfo(Map<String, Object> attributes) {
            this.attributes = attributes;
        }

        public String getId() {
            return String.valueOf(attributes.get("id"));
        }

        public String getName() {
            return (String) attributes.getOrDefault("name", "");
        }

        public String getEmail() {
            return (String) attributes.getOrDefault("email", "");
        }

        public String getImageUrl() {
            return (String) attributes.getOrDefault("avatar_url", "");
        }
    }
} 