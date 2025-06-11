package com.example.myfirstspringbootapp.di_with_assembler.cafe;

public class BasicCoffeeMachine implements CoffeeMachine{
    @Override
    public String brew() {
        return "basic coffee machine";
    }
}
