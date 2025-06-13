package com.captainyun7.ch2codeyourself._05_validation;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

/*
@Getter
@Setter
public class SignupRequest {
    private String username;
    private String password;
    private String email;
    private Integer age;
    private Boolean agreeTerms;
}

레포지토리 단계에서 검증이 없던 기존의 방식
 */

// implementation 'org.springframework.boot:spring-boot-starter-validation' 요구됨

@Getter
@Setter
public class SignupRequest {
    @NotBlank(message = "아이디는 필수입니다.")
    @Size(min = 5, max = 15, message = "아이디는 5~15자리여야 합니다.")
    private String username;

    /*
    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다.")
    // @Pattern(regexp = "[a-zA-Z0-9]") 지루하고 현학적임, 복잡한 여러 조건들을 한 번에 검증하기 쉽지 않음
    // 커스텀 어노테이션을 사용한다!

     */
    @ValidPassword  // 커스텀 어노테이션 하나로 처리 가능
    private String password;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다")   // 이메일 전용 어노테이션 패턴
    private String email;

    @Min(value = 14, message = "만 14세 이상만 가입할 수 있습니다.")
    private Integer age;

    @AssertTrue(message = "약관에 동의해주세요.") // 반드시 true여야 한다는 뜻의 어노테이션
    private Boolean agreeTerms;
}