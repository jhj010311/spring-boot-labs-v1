package com.captainyun7.ch504jwtbasedspringsecuritylogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class ch504jwtbasedspringsecurityloginApplication {

    public static void main(String[] args) {
        SpringApplication.run(ch504jwtbasedspringsecurityloginApplication.class, args);
    }

}
