package com.captainyun7.ch2codeyourself._04_3tiers_crud.exception;

import com.captainyun7.ch2codeyourself._04_3tiers_crud.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 05챕터 학습을 위해 주석처리함(250613 15:44)
//@ControllerAdvice
public class GlobalExceptionHandler {

    // 이 프로젝트 내에서 발생하는 모든 RuntimeException을 catch할 수 있다
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> runtimeException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ErrorResponse> postNotFoundException(PostNotFoundException e) {
        /*
        public ResponseEntity<String> postNotFoundException(PostNotFoundException e)

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

         */

        // ErrorResponse 만든 후 리팩터링

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);


        /*
        응답 예시
        {
            "code": "404 NOT_FOUND",
            "message": "id가 200인 게시글을 찾을 수 없습니다"
        }
        */
    }
}
