package com.tutorial.spring_boot_tutorial.main.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tutorial.spring_boot_tutorial.main.domain.req.MainRequest;
import com.tutorial.spring_boot_tutorial.main.domain.res.MainResponse;

@RestController
public class MainRestController {
    @PostMapping(value = "test")
    public MainResponse mainTest(@RequestBody MainRequest req) {
        MainResponse res = new MainResponse();

        res.setC(req.getA());
        res.setD(req.getB());

        return res;
    }
}
