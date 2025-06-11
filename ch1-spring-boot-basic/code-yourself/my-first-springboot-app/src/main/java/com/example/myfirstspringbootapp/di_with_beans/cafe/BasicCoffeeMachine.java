package com.example.myfirstspringbootapp.di_with_beans.cafe;

import com.example.myfirstspringbootapp.di_with_assembler.cafe.CoffeeMachine;
import org.springframework.stereotype.Component;

@Component
public class BasicCoffeeMachine implements CoffeeMachine {
    @Override
    public String brew() {
        return "basic coffee machine";
    }
}
