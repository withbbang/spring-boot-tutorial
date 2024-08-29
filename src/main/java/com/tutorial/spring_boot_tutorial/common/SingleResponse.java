package com.tutorial.spring_boot_tutorial.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 단일 응답 클래스
 */
@Getter
@Setter
@ToString
public class SingleResponse<T> {
    private Result result;
    private T data;

    public SingleResponse() {
        this.result = new Result();
    }

    public SingleResponse(CodeMessage result) {
        this.result = new Result(result);
    }
}
