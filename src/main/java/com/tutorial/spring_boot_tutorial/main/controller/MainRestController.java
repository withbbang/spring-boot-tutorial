package com.tutorial.spring_boot_tutorial.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tutorial.spring_boot_tutorial.main.domain.req.MainRequest;
import com.tutorial.spring_boot_tutorial.main.domain.res.MainResponse;
import com.tutorial.spring_boot_tutorial.main.service.MainService;

@RestController
public class MainRestController {
    @Autowired
    private MainService mainService;

    @PostMapping(value = "test")
    public MainResponse mainTest(@RequestBody MainRequest req) {
        MainResponse res = mainService.mainService(req);

        return res;
    }
}
