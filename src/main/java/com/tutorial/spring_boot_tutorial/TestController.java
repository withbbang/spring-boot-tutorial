package com.tutorial.spring_boot_tutorial;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@GetMapping("/test")
	public String test() {
		return "test!";
	}

	@GetMapping("/good")
	public String good() {
		return "good!";
	}

	@GetMapping("/bad")
	public String bad() {
		return "bad!";
	}
}
