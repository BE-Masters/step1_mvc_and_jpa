package com.example.be_study.security;

import com.example.be_study.common.jwt.JwtAuthenticationFilter;
import com.example.be_study.common.jwt.JwtService;
import com.example.be_study.common.jwt.JwtTokenUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final JwtTokenUtil jwtTokenUtil;

    private final JwtService jwtService;

    private final static String[] PERMIT_ALL = {
            "/login"
    };

    public WebSecurityConfig(JwtTokenUtil jwtTokenUtil, JwtService jwtService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtService = jwtService;
    }

    @Bean
    @Profile("local")
    public SecurityFilterChain localSecurityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 사용 안 함(토큰 방식 사용)
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests // 권한 설정
                        .requestMatchers(PERMIT_ALL).permitAll()
                        .anyRequest().hasAnyRole("BASIC_USER", "ADMIN")
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenUtil, jwtService), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    @Profile("prod")
    public SecurityFilterChain prodSecurityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 사용 안 함(토큰 방식 사용)
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests // 권한 설정
                        .requestMatchers(PERMIT_ALL).permitAll()
                        .anyRequest().hasAnyRole("BASIC_USER", "ADMIN")
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenUtil, jwtService), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
