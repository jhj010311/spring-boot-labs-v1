package com.example.ch2labs.labs04;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RpsController {

    @GetMapping("/rps")
    @ResponseBody
    public Map<String, String> rps(@RequestParam String user) {
        Map<String, String> response = new HashMap<>();
        

        response.put("user", user);
        //response.put("server", user);
        //response.put("result", user);


        return response;
    }
}
