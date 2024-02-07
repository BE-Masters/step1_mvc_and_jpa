package com.example.be_study.service.user.service;

import com.example.be_study.common.jwt.JwtTokenUtil;
import com.example.be_study.common.jwt.TokenType;
import com.example.be_study.service.user.domain.User;
import com.example.be_study.service.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class UserService {

    public UserRepository userRepository;
    private JwtTokenUtil jwtTokenUtil;

    public UserService(UserRepository userRepository, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Transactional(readOnly = true)
    public List<User> findAllByUserIdIn(Collection<Long> userIds) {
        return userRepository.findAllByIdIn(userIds);
    }

    public User saveUser(User user) {
        User saveUser = userRepository
                .findByProviderKey(user.getProviderKey()).orElseGet(() -> userRepository.save(user));
        log.info("=======================");
        log.info( jwtTokenUtil.createToken(saveUser, TokenType.AccessToken));
        return saveUser;
    }


}
