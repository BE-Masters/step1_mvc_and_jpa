package com.example.be_study.common.jwt;

import com.example.be_study.service.user.domain.User;
import com.example.be_study.service.user.domain.UserRepository;
import com.example.be_study.service.user.enums.UserType;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final JwtTokenUtil jwtTokenUtil;

    private final UserRepository userRepository;

    public JwtService(JwtTokenUtil jwtTokenUtil, UserRepository userRepository) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
    }

    /**
     *  UserType 확인
     */
    public boolean isUnauthorized(String token, TokenType tokenType) {
        Long userId = jwtTokenUtil.getUserId(token, tokenType);
        UserType roles = jwtTokenUtil.getRoles(token, tokenType);

        switch (roles) {
            case BASIC_USER:
                User user = userRepository.findById(userId).orElse(null);
                return user == null;
            default:
                return true;
        }
    }
}
