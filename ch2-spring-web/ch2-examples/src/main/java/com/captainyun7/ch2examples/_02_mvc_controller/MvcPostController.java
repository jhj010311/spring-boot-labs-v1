package com.captainyun7.ch2examples._02_mvc_controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/mvc")
public class MvcPostController {

    private final List<Post> posts = new ArrayList<>();

    // 생성자에서 초기 데이터 추가
    public MvcPostController() {
        posts.add(new Post(1L, "환영합니다", "Thymeleaf 예제입니다."));
        posts.add(new Post(2L, "Spring MVC", "스프링 MVC 컨트롤러 예제입니다."));
    }
    
    /**
     * 포스트 목록 페이지 표시
     */
    @GetMapping("/posts")
    public String listPosts(Model model) {
        model.addAttribute("posts", posts);
        return "posts/list";  // src/main/resources/templates/posts/list.html
    }
    
    /**
     * 포스트 상세 페이지 표시
     */
    @GetMapping("/posts/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        // ID로 포스트 찾기
        Post post = findPostById(id);
        if (post != null) {
            model.addAttribute("post", post);
            return "posts/view";  // src/main/resources/templates/posts/view.html
        } else {
            return "redirect:/mvc/posts";
        }
    }
    
    private Post findPostById(Long id) {
        return posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

} 