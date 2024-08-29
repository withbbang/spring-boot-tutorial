package com.tutorial.spring_boot_tutorial.common;

import lombok.Getter;
import lombok.Setter;

/**
 * 커스텀 Exception
 */
@Getter
@Setter
public class CustomException extends Exception {
    private String code;
    private String message;

    public CustomException(CodeMessage result) {
        super(result.getMessage() + " [" + result.getCode() + "]");
        this.code = result.getCode();
        this.message = result.getMessage();
    }
}
