package com.tutorial.spring_boot_tutorial.main.vo;

import com.tutorial.spring_boot_tutorial.annotations.DatabaseCryptoFieldAnnotation;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MainVo {
    private String id;

    @DatabaseCryptoFieldAnnotation
    private String name;
}
