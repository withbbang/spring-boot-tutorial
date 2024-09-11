package com.tutorial.spring_boot_tutorial.main.service;

import java.util.HashMap;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tutorial.spring_boot_tutorial.common.CodeMessage;
import com.tutorial.spring_boot_tutorial.common.CustomException;
import com.tutorial.spring_boot_tutorial.jwt.JwtTokenProvider;
import com.tutorial.spring_boot_tutorial.main.domain.req.MainRequest;
import com.tutorial.spring_boot_tutorial.main.mapper.MainMapper;
import com.tutorial.spring_boot_tutorial.main.vo.MainVo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainService {
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    MainMapper mainMapper;

    private final ModelMapper modelMapper;

    public MainVo mainService(MainRequest req) {
        return mainMapper.mainMapper(req);
    }

    @Transactional
    public void mainUpdate(MainRequest req) {
        mainMapper.mainUpdate1(req);
        mainMapper.mainUpdate2(req);

        throw new RuntimeException("트랜잭션 테스트 에러발생");
    }

    public Map<String, String> login(MainRequest req) throws Exception {
        MainVo vo = mainMapper.getSingleTest(req.getName());

        if (vo == null) {
            throw new CustomException(CodeMessage.ER0001);
        }

        MainVo newVo = modelMapper.map(vo, MainVo.class);

        Map<String, String> token = jwtTokenProvider.createToken(newVo);

        return token;
    }
}
