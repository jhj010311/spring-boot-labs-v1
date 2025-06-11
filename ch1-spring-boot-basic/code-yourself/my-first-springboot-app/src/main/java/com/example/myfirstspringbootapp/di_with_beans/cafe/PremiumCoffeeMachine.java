package com.example.myfirstspringbootapp.di_with_beans.cafe;

import com.example.myfirstspringbootapp.di_with_assembler.cafe.CoffeeMachine;

public class PremiumCoffeeMachine implements CoffeeMachine {
    @Override
    public String brew() {
        return "premium coffee machine";
    }
}
