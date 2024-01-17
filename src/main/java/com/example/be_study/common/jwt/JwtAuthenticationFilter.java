package com.example.be_study.common.jwt;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info(" ### JwtAuthenticationFilter ### ");

        String token = jwtTokenUtil.resolveToken(request); // Header 에서 토큰 값 가져오기

        // 토큰이 없는 경우, 만료된 토큰인 경우, 권한이 없는 경우에만 예외 던지기
        if (StringUtils.isNotBlank(token) && !jwtTokenUtil.isExpired(token, TokenType.AccessToken) && !jwtTokenUtil.isUnauthorized(token, TokenType.AccessToken)) {
            Authentication authentication = jwtTokenUtil.getAuthentication(token, TokenType.AccessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

}
