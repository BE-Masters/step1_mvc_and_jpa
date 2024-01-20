package com.example.be_study.common.jwt;

import com.example.be_study.common.jwt.properties.JwtProperties;
import com.example.be_study.service.user.domain.User;
import com.example.be_study.service.user.enums.UserType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {

    private final JwtProperties jwtProperties;

    public JwtTokenUtil(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String createToken(User user, TokenType tokenType) {
        return this.createToken(user.getId(), UserType.BASIC_USER, tokenType);
    }

    /**
     *  JWT 생성
     */
    private String createToken(Long userId, UserType role, TokenType tokenType) {
        String secretKey = TokenType.AccessToken.equals(tokenType) ? jwtProperties.getAccessTokenKey() : jwtProperties.getRefreshTokenKey();

        Claims claims = Jwts.claims().setSubject(String.valueOf(userId)); // JWT payload 에 저장되는 정보 단위
        claims.put("roles", role);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간
                .setExpiration(new Date(now.getTime() + tokenType.getExpirationTime())) // 만료 기간 설정
                .signWith(SignatureAlgorithm.HS256, secretKey) // 사용할 암호화 알고리즘과 signature 에 들어갈 secretKey
                .compact();
    }

    public Claims getClaims(String token, TokenType tokenType) {
        String secretKey = TokenType.AccessToken.equals(tokenType) ? jwtProperties.getAccessTokenKey() : jwtProperties.getRefreshTokenKey();
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    /**
     *  권한 정보 획득
     */
    public UsernamePasswordAuthenticationToken getAuthentication(String token, TokenType tokenType) {
        Claims claims = this.getClaims(token, tokenType);
        List<String> roles = new ArrayList<>();
        roles.add(claims.get("roles", String.class));

        Collection<? extends GrantedAuthority> getAuthorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(claims, "", getAuthorities);
    }

    /**
     *  Request Header 에서 토큰 값 가져오기
     */
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (!StringUtils.hasText(bearerToken)) {
            return null;
        }

        if (!bearerToken.startsWith("Bearer ")) {
            return null;
        }

        return bearerToken.split(" ")[1].trim();
    }

    /**
     *  토큰 유효성 검사 및 만료 여부 확인
     */
    public boolean isExpired(String token, TokenType tokenType) {
        try {
            return this.getClaims(token, tokenType).getExpiration().before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    /**
     *  토큰에서 사용자 정보 추출
     */
    public Long getUserId(String token, TokenType tokenType) {
        String result = this.getClaims(token, tokenType).getSubject();
        return Long.parseLong(result);
    }

    /**
     *  토큰에서 권한 확인
     */
    public UserType getRoles(String token, TokenType tokenType) {
        return UserType.valueOf(String.valueOf(this.getClaims(token, tokenType).get("roles")));
    }
}
