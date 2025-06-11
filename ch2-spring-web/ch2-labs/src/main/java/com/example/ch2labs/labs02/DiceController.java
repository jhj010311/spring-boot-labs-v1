package com.example.ch2labs.labs02;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DiceController {

    @GetMapping("/dice")
    @ResponseBody
    public Map<String, Integer> dice() {
        Map<String, Integer> response = new HashMap<>();
        int dice = (int) Math.floor(Math.random() * 6 + 1);

        response.put("dice", dice);


        return response;
    }
}
