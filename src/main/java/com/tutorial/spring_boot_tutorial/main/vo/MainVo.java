package com.tutorial.spring_boot_tutorial.main.vo;

import com.tutorial.spring_boot_tutorial.annotations.Test2Annotation;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MainVo {
    private String id;

    @Test2Annotation(test2Annotation = "test2Annotation")
    private String name;
}
