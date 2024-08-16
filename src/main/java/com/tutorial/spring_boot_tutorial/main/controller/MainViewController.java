package com.tutorial.spring_boot_tutorial.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainViewController {
    @GetMapping("/")
    public String main() {
        return "main";
    }
}
