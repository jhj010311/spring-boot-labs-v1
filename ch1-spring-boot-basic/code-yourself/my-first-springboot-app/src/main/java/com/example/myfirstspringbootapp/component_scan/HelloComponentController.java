package com.example.myfirstspringbootapp.component_scan;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloComponentController {
    private final HelloComponentBean component;
    // 원래 자바에선 클래스를 사용하기 위해 인스턴스를 new로 생성해야 했다
    // @Component 어노테이션이 @SpringBootApplication의 @ComponentScan에 스캔되면
    // Bean을 만들어주고, 인스턴스를 new로 생성하지 않고도 사용이 가능하다

    public HelloComponentController(HelloComponentBean component) {
        this.component = component;
    }

    @GetMapping("/hello-component")
    public String hello() {
        return component.sayHello();
    }
}
