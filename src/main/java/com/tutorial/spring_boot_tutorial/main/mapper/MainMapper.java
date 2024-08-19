package com.tutorial.spring_boot_tutorial.main.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.tutorial.spring_boot_tutorial.main.domain.req.MainRequest;
import com.tutorial.spring_boot_tutorial.main.domain.res.MainResponse;

@Mapper
@Repository
public interface MainMapper {
    MainResponse mainMapper(MainRequest req);
}
