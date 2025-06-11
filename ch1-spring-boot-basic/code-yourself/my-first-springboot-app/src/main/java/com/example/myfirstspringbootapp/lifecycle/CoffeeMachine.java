package com.example.myfirstspringbootapp.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component("coffeeBean")    // bean 이름 사용자지정 가능
public class CoffeeMachine {
    public CoffeeMachine() {
        System.out.println("1. 커피머신 연결 (생성자)");
    }

    @PostConstruct  // 생성 후에 실행될 수 있음
    public void heatUp() {
        System.out.println("2. 물 데우기 준비 ... ");
    }

    public void makeCoffee() {
        System.out.println("3. 커피머신이 커피를 내립니다 ... ");
    }

    @PreDestroy // 소멸 전에 실행
    public void shutDown() {
        System.out.println("4. 커피머신의 전원을 끕니다.");
    }
}
