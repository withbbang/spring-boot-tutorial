package com.tutorial.spring_boot_tutorial.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.tutorial.spring_boot_tutorial.main.vo.MainVo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class UserDetailsByJwt implements UserDetails {

    private final MainVo vo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_" + vo.getGrade());


        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return vo.getPassword();
    }

    @Override
    public String getUsername() {
        return vo.getName().toString();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
