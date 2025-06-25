package com.captainyun7.ch501sessionbasedplainlogin.config;

import com.captainyun7.ch501sessionbasedplainlogin.interceptor.AuthorizationInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 어노테이션 필수
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    // TODO
    private final AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/auth/**");
        // addPathPatterns에 지정된 경로 안의 것들을 가로채겠다
        // 개중에서 exclude 안의 경로는 패스한다
    }
} 