package com.example.ch2labs.labs08;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationRequest {
    @NotBlank(message = "아이디는 필수입니다.")
    @Size(min =5, max = 12, message = "아이디는 5~12자여야 합니다.")
    @Pattern(regexp = "[a-z0-9]", message = "아이디는 알파벳 소문자와 숫자가 포함되어야 합니다.")
    private String userId;


    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
