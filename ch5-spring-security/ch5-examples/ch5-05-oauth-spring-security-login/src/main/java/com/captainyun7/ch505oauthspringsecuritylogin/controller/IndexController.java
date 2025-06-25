package com.captainyun7.ch505oauthspringsecuritylogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping("/")
    @ResponseBody
    public String index() {
        return """
                <h1>Spring Security OAuth2 예제</h1>
                <p>GitHub 계정으로 로그인할 수 있습니다.</p>
                <a href="/oauth2/authorize/github">GitHub로 로그인</a>
                <br>
                <p>또는</p>
                <a href="/api/auth/oauth2-providers">OAuth2 제공자 정보 가져오기 (API)</a>
                """;
    }

    @GetMapping("/login")
    @ResponseBody
    public String login() {
        return """
                <h1>로그인 페이지</h1>
                <a href="/oauth2/authorize/github">GitHub로 로그인</a>
                """;
    }
} 