package com.example.be_study.choi.services.User.service;

import com.example.be_study.choi.services.User.domain.UserUpdateRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserUpdateRepository userUpdateRepository;

    public UserService(UserUpdateRepository userUpdateRepository) {
        this.userUpdateRepository = userUpdateRepository;
    }

    public void findAllByUserId(Long userId){
        userUpdateRepository.findAllByUserId(userId);
    }
}
