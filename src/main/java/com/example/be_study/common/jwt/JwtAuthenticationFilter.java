package com.example.be_study.common.jwt;

import com.example.be_study.common.response.DataResponse;
import com.example.be_study.service.user.service.UserService;
import io.jsonwebtoken.Claims;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenUtil.resolveToken(request); // Header 에서 토큰 값 가져오기

        debugLogging(request);

        if (StringUtils.isNotBlank(token)) {
            tokenValidation(token);
            UserDetails userDetails = getUserPrincipal(token);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private void debugLogging(HttpServletRequest request) {
        log.info("<< 요청  Request (" + request.getMethod() + ") " + request.getHeader("Device-Type") + " - " + request.getRequestURL() + ">>");
    }

    private void tokenValidation(String token) {
        if (jwtTokenUtil.isExpired(token, TokenType.AccessToken)) { // 만료된 토큰인 경우
            new DataResponse<>(JwtResponseMessage.TOKEN_EXPIRED_MESSAGE);
        }

        if (jwtService.isUnauthorized(token, TokenType.AccessToken)) { // 권한이 없는 경우
            new DataResponse<>(JwtResponseMessage.TOKEN_PERMISSION_ERROR_MESSAGE);
        }
    }

    private UserDetails getUserPrincipal(String token){
        Claims claims = jwtTokenUtil.getClaims(token, TokenType.AccessToken);
        return userService.loadUserByUserName(Long.parseLong(claims.getSubject()), claims);
    }
}
