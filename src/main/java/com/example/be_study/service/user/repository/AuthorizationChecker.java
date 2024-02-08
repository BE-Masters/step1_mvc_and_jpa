package com.example.be_study.service.user.repository;

import com.example.be_study.service.user.enums.UserType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AuthorizationChecker {

    @Before("@annotation(AdminOnly)")
    public void check(JoinPoint joinPoint){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Authorities: " + authentication.getAuthorities().toString());
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

        log.info("Is Admin: " + isAdmin);
        if (!isAdmin){
            throw new AccessDeniedException("Admin 권한이 없습니다. ");
        }
    }

}
