package com.captainyun7.ch2codeyourself._02_mvc_controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mvc")
public class MvcController {

    /*
    // /mvc/basic/view
    @GetMapping("/basic/view")
    public String basicView() {
        return "basic-view";
    }
    // 이 상태에선 안 나오는 것이 정상(500 error?)
    // html SSR 방식에선 파일이 있어야한다
    // jsp나 타임리프 등 템플릿 엔진 기술이 필요

    */

    @GetMapping("/basic/view")
    public String basicView() {
        return "basic-view";    // view resolver
    }
    // thymeleaf를 적용하고 basic-view html 파일을 생성하니 연결됨
    // 그러나 이 방식으론 변화를 줄 수 없이 미리 생성해둔 페이지만 보여줄 수 있음


    // mvc 패턴 : Model View Controller
    // 유지보수 용이, 역할분리
    @GetMapping("/model")
    public String model(Model model) {
        // import org.springframework.ui.Model;
        // MVC 모델에선 동적인 데이터를 Model 객체에 담아서 넘길 수 있다

        model.addAttribute("msg", "Hello World");
        model.addAttribute("currentTime", java.time.LocalDateTime.now());
        return "model-view";
    }
}
