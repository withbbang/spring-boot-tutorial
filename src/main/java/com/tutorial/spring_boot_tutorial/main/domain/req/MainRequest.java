package com.tutorial.spring_boot_tutorial.main.domain.req;

import com.tutorial.spring_boot_tutorial.annotations.TestAnnotation;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MainRequest {
    @TestAnnotation(value = "a")
    private String a;

    @TestAnnotation(value = "b")
    private String b;
}
