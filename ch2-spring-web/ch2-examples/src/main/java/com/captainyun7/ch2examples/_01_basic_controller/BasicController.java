package com.captainyun7.ch2examples._01_basic_controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class BasicController {

    @ResponseBody
    @GetMapping("/basic/hello")
    public String hello() {
        return "Hello, Spring Web!";
    }

    @ResponseBody
    @GetMapping("/basic/users/{userId}")
    public String pathVariableExample(@PathVariable Long userId) {
        return "User ID: " + userId;
    }

    @ResponseBody
    @GetMapping("/basic/users/{userId}/orders/{orderId}")
    public String pathVariablesExample(@PathVariable Long userId,
                                       @PathVariable Long orderId) {
        return "User ID: " + userId + "Order ID: " + orderId;
    }

    @ResponseBody
    @GetMapping("/basic/params")
    public String requestParamExample(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int age) {
        return "Name: " + name + ", Age: " + age;
    }

    @ResponseBody
    @GetMapping("/basic/filter")
    public String filter(@RequestParam Map<String, String> params) {
        return "전체 파라미터: " + params.toString();
    }

    @ResponseBody
    @PostMapping("/basic/users")
    public String createUser() {
        return "사용자 생성 요청";
    }

    @ResponseBody
    @PutMapping("/basic/users/{userId}")
    public String updateUser(@PathVariable Long userId) {
        return "사용자 " + userId + " 수정 요청";
    }

    @ResponseBody
    @DeleteMapping("/basic/users/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        return "사용자 " + userId + " 삭제 요청";
    }
} 