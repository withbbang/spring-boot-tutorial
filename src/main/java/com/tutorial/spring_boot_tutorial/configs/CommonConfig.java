package com.tutorial.spring_boot_tutorial.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
