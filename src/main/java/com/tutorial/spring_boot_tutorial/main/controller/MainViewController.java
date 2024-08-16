package com.tutorial.spring_boot_tutorial.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainViewController {
    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("main", "Macho");

        return "main";
    }
}
