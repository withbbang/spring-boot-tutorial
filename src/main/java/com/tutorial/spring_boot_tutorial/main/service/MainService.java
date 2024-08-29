package com.tutorial.spring_boot_tutorial.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tutorial.spring_boot_tutorial.main.domain.req.MainRequest;
import com.tutorial.spring_boot_tutorial.main.mapper.MainMapper;
import com.tutorial.spring_boot_tutorial.main.vo.MainVo;

@Service
public class MainService {
    @Autowired
    MainMapper mainMapper;

    public MainVo mainService(MainRequest req) {
        return mainMapper.mainMapper(req);
    }

    @Transactional
    public void mainUpdate(MainRequest req) {
        mainMapper.mainUpdate1(req);
        mainMapper.mainUpdate2(req);

        // throw new RuntimeException("트랜잭션 테스트 에러발생");
    }
}
