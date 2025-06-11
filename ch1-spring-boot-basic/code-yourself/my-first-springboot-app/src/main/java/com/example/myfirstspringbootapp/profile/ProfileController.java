package com.example.myfirstspringbootapp.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ProfileController {

    @Autowired
    Environment environment;

    @GetMapping("/profile")
    public ArrayList<String> profile() {
        ArrayList<String> list = new ArrayList<>();
        list.add(environment.getProperty("app.name"));
        list.add(environment.getProperty("app.version"));
        list.add(environment.getProperty("app.message"));

        return list;
    }
}
