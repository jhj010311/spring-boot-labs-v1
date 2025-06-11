package com.example.myfirstspringbootapp.di_with_assembler.cafe;

public class PremiumCoffeeMachine implements CoffeeMachine{
    @Override
    public String brew() {
        return "premium coffee machine";
    }
}
