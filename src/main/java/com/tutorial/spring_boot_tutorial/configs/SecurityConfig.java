package com.tutorial.spring_boot_tutorial.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.tutorial.spring_boot_tutorial.jwt.JwtAccessDeniedHandler;
import com.tutorial.spring_boot_tutorial.jwt.JwtAuthFilter;
import com.tutorial.spring_boot_tutorial.jwt.JwtAuthenticationEntryPoint;
import com.tutorial.spring_boot_tutorial.jwt.JwtTokenProvider;
import com.tutorial.spring_boot_tutorial.jwt.UserDetailsServiceByJwt;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig {
    private final UserDetailsServiceByJwt userDetailsServiceByJwt;
    private final JwtTokenProvider jwtTokenProvider;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    private static final String[] AUTH_WHITELIST = {"/", "/login"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CSRF, CORS
        http.csrf((csrf) -> csrf.disable());
        http.cors(Customizer.withDefaults());

        // 세션 관리 상태 없음으로 구성, Spring Security가 세션 생성 or 사용 X
        http.sessionManagement(sessionManagement -> sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // FormLogin, BasicHttp 비활성화
        http.formLogin((form) -> form.disable());
        http.httpBasic(AbstractHttpConfigurer::disable);


        // JwtAuthFilter를 UsernamePasswordAuthenticationFilter 앞에 추가
        http.addFilterBefore(new JwtAuthFilter(userDetailsServiceByJwt, jwtTokenProvider),
                UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling((exceptionHandling) -> exceptionHandling
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler));

        // 권한 규칙 작성
        http.authorizeHttpRequests(
                authorize -> authorize.requestMatchers(AUTH_WHITELIST).permitAll()
                        // @PreAuthrization을 사용할 것이기 때문에 모든 경로에 대한 인증처리는 Pass
                        .anyRequest().permitAll()
        // .anyRequest().authenticated()
        );

        return http.build();
    }
}
