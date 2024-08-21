package com.tutorial.spring_boot_tutorial.main.domain.res;

import com.tutorial.spring_boot_tutorial.annotations.Test2Annotation;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MainResponse {
    private String id;

    @Test2Annotation(value = "a")
    private String name;
}
