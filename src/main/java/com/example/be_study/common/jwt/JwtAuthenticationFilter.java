package com.example.be_study.common.jwt;

import com.example.be_study.common.error.exception.TokenExpiredException;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil, JwtService jwtService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenUtil.resolveToken(request); // Header 에서 토큰 값 가져오기

//        debugLogging(request);
//        tokenValidation(token);

//        Authentication authentication = jwtTokenUtil.getAuthentication(token, TokenType.AccessToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    private void debugLogging(HttpServletRequest request) {
        log.info("<< 요청  Request (" + request.getMethod() + ") " + request.getHeader("Device-Type") + " - " + request.getRequestURL() + ">>");
    }

    private void tokenValidation(String token) {
        if (!StringUtils.isNotBlank(token)) { // 토큰이 없는 경우
            throw new TokenExpiredException();
        }

        if (jwtTokenUtil.isExpired(token, TokenType.AccessToken)) { // 만료된 토큰인 경우
            throw new TokenExpiredException();
        }

        if (jwtService.isUnauthorized(token, TokenType.AccessToken)) { // 권한이 없는 경우
            throw new TokenExpiredException();
        }
    }

}
