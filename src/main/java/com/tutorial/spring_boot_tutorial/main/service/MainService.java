package com.tutorial.spring_boot_tutorial.main.service;

import org.springframework.stereotype.Service;
import com.tutorial.spring_boot_tutorial.main.domain.req.MainRequest;
import com.tutorial.spring_boot_tutorial.main.domain.res.MainResponse;

@Service
public class MainService {
    public MainResponse mainService(MainRequest req) {
        MainResponse res = new MainResponse();

        res.setC(req.getA() + " A");
        res.setD(req.getB() + " B");

        return res;
    }
}
