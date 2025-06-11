package com.example.myfirstspringbootapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public HelloConfigBean helloConfigBean() {
        return new HelloConfigBean();
    }
}
