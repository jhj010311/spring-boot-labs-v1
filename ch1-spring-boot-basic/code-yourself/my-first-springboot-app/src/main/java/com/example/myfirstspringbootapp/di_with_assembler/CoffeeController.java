package com.example.myfirstspringbootapp.di_with_assembler;

import com.example.myfirstspringbootapp.di_with_assembler.cafe.Barista;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoffeeController {

    @GetMapping("/coffee")
    public String coffee() {
        return Assembler.assemble();
    }
}
