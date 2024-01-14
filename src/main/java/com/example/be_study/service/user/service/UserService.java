package com.example.be_study.service.user.service;

import com.example.be_study.service.user.domain.User;
import com.example.be_study.service.user.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class UserService {

    public UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<User> findAllByUserIdIn(Collection<Long> userIds) {
        return userRepository.findAllByUserIdIn(userIds);
    }
}
