package com.example.myfirstspringbootapp.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("dev")
public class DevController {
    // Profile 어노테이션을 사용하면 현재 사용중인 yml 프로파일을 인식해서 맞는 bean만 생성한다

    @GetMapping("/dev")
    public String dev() {
        return "개발환경";
    }
}
