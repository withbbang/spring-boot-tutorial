package com.tutorial.spring_boot_tutorial.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SingleResponse<T> {
    public SingleResponse() {}

    public SingleResponse(T data) {
        this.data = data;
    }

    private T data;
}
