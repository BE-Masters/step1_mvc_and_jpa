package com.example.be_study.service.admin.annotation;

import com.example.be_study.common.jwt.JwtTokenUtil;
import com.example.be_study.common.jwt.TokenType;
import com.example.be_study.service.user.enums.UserResponseMessage;
import com.example.be_study.service.user.enums.UserType;
import com.example.be_study.service.user.exception.UserBadRequestApiException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthAdminInterceptor implements HandlerInterceptor {

    private final JwtTokenUtil jwtTokenUtil;

    public AuthAdminInterceptor(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod handlerMethod) {
            AuthAdmin authAdmin = handlerMethod.getMethodAnnotation(AuthAdmin.class);
            if (authAdmin != null) {
                String accessToken = request.getHeader("Authorization");
                accessToken = accessToken.substring(7);

                if (jwtTokenUtil.getRoles(accessToken, TokenType.AccessToken).equals(UserType.ADMIN)) {
                    return true;
                } else {
                    throw new UserBadRequestApiException(UserResponseMessage.INVALID_AUTH_USER);
                }
            }
        }
        return true;
    }
}
