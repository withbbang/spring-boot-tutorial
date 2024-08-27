package com.tutorial.spring_boot_tutorial.main.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.tutorial.spring_boot_tutorial.main.domain.req.MainRequest;
import com.tutorial.spring_boot_tutorial.main.vo.MainVo;

@Mapper
@Repository
public interface MainMapper {
    MainVo mainMapper(MainRequest req);

    void mainUpdate1(MainRequest req);

    void mainUpdate2(MainRequest req);
}
