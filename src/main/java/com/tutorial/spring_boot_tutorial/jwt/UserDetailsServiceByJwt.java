package com.tutorial.spring_boot_tutorial.jwt;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.tutorial.spring_boot_tutorial.main.mapper.MainMapper;
import com.tutorial.spring_boot_tutorial.main.vo.MainVo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceByJwt implements UserDetailsService {
    private final MainMapper mainMapper;
    private final ModelMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        MainVo vo = mainMapper.getSingleTest(id);

        MainVo newVo = mapper.map(vo, MainVo.class);

        return new UserDetailsByJwt(newVo);
    }
}
