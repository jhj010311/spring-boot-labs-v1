package com.example.ch2labs.labs08;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/reserve")
public class ReserveController {

    @PostMapping
    public ResponseEntity<Map<String, String>> signup(@RequestBody @Valid ReservationRequest request) {
        Map<String, String> response = new HashMap<>();

        response.put("message", request.getUserId() + "님의 예약이 완료되었습니다.");
        return ResponseEntity.ok(response);

    }
}
