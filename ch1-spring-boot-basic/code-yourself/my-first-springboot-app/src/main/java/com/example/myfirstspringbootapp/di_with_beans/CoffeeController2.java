package com.example.myfirstspringbootapp.di_with_beans;

import com.example.myfirstspringbootapp.di_with_beans.cafe.Barista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoffeeController2 {

    @Autowired
    Barista barista;

//    public CoffeeController2(Barista barista) {
//        this.barista = barista;
//    }
    // Autowired 생략 가능

//    @Autowired
//    public void setBarista(Barista barista) {
//        this.barista = barista;
//    }
    // Autowired는 써야함
    // 다만 그렇게 쓰기 좋은 방식은 아님
    

    @GetMapping("/coffee-bean")
    public String coffee() {
        return barista.makeCoffee();
    }
}
