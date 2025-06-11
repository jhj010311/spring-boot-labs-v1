package com.captainyun7.ch2codeyourself._01_basic_controller;

// implementation 'org.springframework.boot:spring-boot-starter-web'
// build.gradle에 있는 위의 의존성이 많은 것을 가능하게 한다

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class BasicController {

    /*
    // GET 방식으로 /basic/hello 요청이 왔을 때 hello() 실행
    @GetMapping("/basic/hello")
    public String hello() {
        return "hello";
    }
    // 이 상태로 하면 화이트라벨 화면이 뜬다
    // MVC 뷰 'hello'을(를) 해결할 수 없습니다
    // "hello"는 스프링에선 문자열이 아니다!
    // view를 넘겨야 한다
    */

    // GET 방식으로 /basic/hello 요청이 왔을 때 hello() 실행
    @GetMapping("/basic/hello")
    @ResponseBody   // HTTP의 body에 담도록 설정
    public String hello() {
        return "hello"; // 데이터로서 삽입된다
    }

    // 동적인 {경로 변수}
    @GetMapping("/basic/users/{userId}")
    @ResponseBody
    public String users(@PathVariable int userId) {
        return "user ID: " + userId;
    }

    @GetMapping("/basic/users/{userId}/orders/{orderId}")
    @ResponseBody
    public String userOrders(@PathVariable int userId, @PathVariable int orderId) {
        return "user ID: " + userId + "\norder ID: " + orderId;
    }


    // /basic/params?name=name&age=10
    @ResponseBody
    @GetMapping("/basic/params")
    public String params(@RequestParam String name, @RequestParam int age) {
        return "Name: " + name + "\nAge: " + age;
    }


    // 파라미터 가짓수가 고정되지 않은 경우
    @ResponseBody
    @GetMapping("/basic/filter")
    public String filter(@RequestParam Map<String, String> params) {
        return "전체 파라미터:\n" + params;
    }


    @PostMapping("/basic/users")
    @ResponseBody
    public String post() {
        return "사용자 생성 성공";
    }

    @PutMapping("/basic/users/{userId}")
    @ResponseBody
    public String put() {
        return "사용자 수정 성공";
    }

    @DeleteMapping("/basic/users/{userId}")
    @ResponseBody
    public String delete() {
        return "사용자 삭제 성공";
    }
}
