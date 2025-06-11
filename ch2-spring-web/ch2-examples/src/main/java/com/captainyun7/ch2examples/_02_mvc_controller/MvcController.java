package com.captainyun7.ch2examples._02_mvc_controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/mvc")
public class MvcController {

    /**
     * ViewName을 반환하는 기본 컨트롤러
     */
    @GetMapping("/basic/view")
    public String basicView() {
        return "basic-view";  // src/main/resources/templates/basic-view.html을 찾음
    }
    
    /**
     * Model 객체에 데이터를 추가하여 뷰로 전달
     */
    @GetMapping("/model-example")
    public String modelExample(Model model) {
        model.addAttribute("message", "Thymeleaf에서 사용할 메시지입니다.");
        model.addAttribute("currentTime", java.time.LocalDateTime.now());

        Map<String, Object> user = new HashMap<>();
        user.put("name", "윤유저");
        user.put("email", "yun@example.com");
        model.addAttribute("user", user);
        
        return "model-example";  // src/main/resources/templates/model-example.html
    }

} 