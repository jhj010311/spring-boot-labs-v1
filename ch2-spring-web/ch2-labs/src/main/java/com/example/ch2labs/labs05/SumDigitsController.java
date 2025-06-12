package com.example.ch2labs.labs05;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SumDigitsController {

    @GetMapping("/sum-digits")
    public ResponseEntity<Map<String, String>> sumDigits(@RequestParam(required = false) String number){
        Map<String, String> response = new HashMap<>();

        if(number == null || number.isEmpty()) {
            response.put("error", "number 파라미터는 필수입니다.");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else {
            try {
                int num = Integer.parseInt(number);

                if(num <= 0) {
                    response.put("error", "음수는 지원하지 않습니다. 양의 정수를 입력해주세요.");

                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                } else {
                    int sum = 0;

                    for(int i = 0; i < number.length(); i++) {
                        sum += num % 10;
                        num /= 10;
                    }

                    response.put("message", "각 자리수 합: " + sum);

                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                }
            } catch (NumberFormatException e) {
                response.put("error", "정수만 입력 가능합니다. 예: /sum-digits?number=1234");

                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
            }
        }
    }
}
