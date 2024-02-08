package com.example.be_study.security;

import com.example.be_study.common.jwt.JwtTokenUtil;
import com.example.be_study.service.admin.annotation.AuthAdminInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final JwtTokenUtil jwtTokenUtil;

    public WebConfig(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthAdminInterceptor(jwtTokenUtil));
    }
}
