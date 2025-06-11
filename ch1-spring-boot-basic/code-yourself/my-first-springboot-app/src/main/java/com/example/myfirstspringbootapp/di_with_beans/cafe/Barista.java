package com.example.myfirstspringbootapp.di_with_beans.cafe;

import com.example.myfirstspringbootapp.di_with_assembler.cafe.CoffeeMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Barista {
    private CoffeeMachine machine;

    @Autowired
    public Barista(CoffeeMachine machine) {
        this.machine = machine;
    }

    public String makeCoffee() {
        return machine.brew();
    }
}
