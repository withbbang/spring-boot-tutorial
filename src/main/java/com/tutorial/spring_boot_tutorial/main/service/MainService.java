package com.tutorial.spring_boot_tutorial.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public void mainUpdate1(MainRequest req) {
        mainMapper.mainUpdate1(req);
    }

    @Transactional
    public void mainUpdate2(MainRequest req) {
        mainMapper.mainUpdate2(req);

        throw new RuntimeException("트랜잭션 테스트 에러발생");
    }
}
