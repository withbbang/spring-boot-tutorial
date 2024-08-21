package com.tutorial.spring_boot_tutorial.main.domain.req;

import com.tutorial.spring_boot_tutorial.annotations.TestAnnotation;
import com.tutorial.spring_boot_tutorial.annotations.Test2Annotation;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MainRequest {
    @TestAnnotation(value = "a")
    @Test2Annotation(value = "b")
    private String id;

    @Test2Annotation(value = "c")
    @TestAnnotation(value = "d")
    private String name;
}
