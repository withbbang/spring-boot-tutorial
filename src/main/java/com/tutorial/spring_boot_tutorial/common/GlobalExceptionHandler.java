package com.tutorial.spring_boot_tutorial.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<SingleResponse<Object>> handleCustomException(CustomException ex) {
        Result result = new Result(ex.getCode(), ex.getMessage());
        SingleResponse<Object> response = new SingleResponse<>();
        response.setResult(result);
        response.setData(null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
