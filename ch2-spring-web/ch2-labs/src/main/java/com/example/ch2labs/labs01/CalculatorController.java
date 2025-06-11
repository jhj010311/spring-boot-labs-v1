package com.example.ch2labs.labs01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CalculatorController {

    @GetMapping("/calc")
    @ResponseBody
    public String calc(@RequestParam int x, @RequestParam int y, @RequestParam String op){
        int result = 0;
        String oper = "";

        if(op.equals("add")){
            result = x + y; oper = "+";
        } else if(op.equals("sub")){
            result = x - y; oper = "-";
        } else if(op.equals("mul")){
            result = x * y; oper = "*";
        } else if(op.equals("div")){
            result = x / y; oper = "/";
        }


        return "결과 : " + x + " " + oper + " " + y + " = " + result;
    }
}
