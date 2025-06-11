package com.example.ch2labs.labs03;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RandomNumberController {

    @GetMapping("/random")
    @ResponseBody
    public Map<String, Integer> dice(@RequestParam int min, @RequestParam int max) {
        Map<String, Integer> response = new HashMap<>();
        int number = (int) Math.floor(Math.random() * max + min);

        response.put("number", number);


        return response;
    }
}
