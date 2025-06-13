package com.captainyun7.ch2codeyourself._05_validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "비밀번호는 8자 이상, 숫자/대문자/특수문자를 포함해야 합니다.";
    // 인터페이스의 속성 (다른 어노테이션의 min, message, value 등의 필드) 정의

    // 이 자체로는 검증 불가 >> @Constraint를 통해서 검증할 클래스를 지정


    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
