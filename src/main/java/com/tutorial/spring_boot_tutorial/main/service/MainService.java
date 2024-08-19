package com.tutorial.spring_boot_tutorial.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tutorial.spring_boot_tutorial.main.domain.req.MainRequest;
import com.tutorial.spring_boot_tutorial.main.domain.res.MainResponse;
import com.tutorial.spring_boot_tutorial.main.mapper.MainMapper;

@Service
public class MainService {
    @Autowired
    MainMapper mainMapper;

    public MainResponse mainService(MainRequest req) {
        return mainMapper.mainMapper(req);
    }
}
