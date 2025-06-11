package com.example.myfirstspringbootapp.environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvController {

    @Autowired
    Environment env;

    @Value("${app.name}")   // 바인딩
    private String appName;


    @GetMapping("/env")
    public String printEnv() {
        return appName;
    }
}
