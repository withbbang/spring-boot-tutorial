package com.tutorial.spring_boot_tutorial.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Result {
    private String code;
    private String message;

    public Result() {
        this.code = CodeMessage.SUCCESS.getCode();
        this.message = CodeMessage.SUCCESS.getMessage();
    }

    public Result(CodeMessage result) {
        this.code = result.getCode();
        this.message = result.getMessage();
    }
}
