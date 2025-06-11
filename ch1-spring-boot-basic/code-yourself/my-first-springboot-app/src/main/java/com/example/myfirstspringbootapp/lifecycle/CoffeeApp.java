package com.example.myfirstspringbootapp.lifecycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CoffeeApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CoffeeApp.class, args);

        CoffeeMachine machine = context.getBean(CoffeeMachine.class);
        machine.makeCoffee();

        context.close();
    }
}
