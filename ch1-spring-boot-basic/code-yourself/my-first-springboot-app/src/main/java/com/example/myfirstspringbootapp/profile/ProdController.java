package com.example.myfirstspringbootapp.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("prod")
public class ProdController {

    @GetMapping("/prod")
    public String prod() {
        return "운영환경";
    }
}
