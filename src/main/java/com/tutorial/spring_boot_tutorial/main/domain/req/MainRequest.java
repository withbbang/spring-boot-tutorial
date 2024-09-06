package com.tutorial.spring_boot_tutorial.main.domain.req;

import com.tutorial.spring_boot_tutorial.annotations.RequiredAnnotation;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MainRequest {
    @RequiredAnnotation
    private String name;

    @RequiredAnnotation
    private String password;
}
