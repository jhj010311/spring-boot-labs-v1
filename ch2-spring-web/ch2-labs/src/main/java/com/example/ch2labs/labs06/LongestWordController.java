package com.example.ch2labs.labs06;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LongestWordController {

    @PostMapping("/longest-word")
    public ResponseEntity<Map<String, String>> longestWord(@RequestBody Words request) {
        Map<String, String> response = new HashMap<>();

        String[] words = request.getWords();

        if(words == null || words.length == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else if (words.length > 10) {
            response.put("error", "10개를 넘는 경우 처리할 수 없습니다");

            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
        }

        String answer = "";

        for(String str : words) {
            if(answer.length() < str.length()) answer = str;
        }

        response.put("message", "가장 긴 단어는 " + answer + "입니다");

        return ResponseEntity.ok(response);
    }
}
