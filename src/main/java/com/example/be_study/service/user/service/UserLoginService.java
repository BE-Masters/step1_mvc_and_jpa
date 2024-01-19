package com.example.be_study.service.user.service;

import com.example.be_study.common.error.exception.BadRequestApiException;
import com.example.be_study.common.response.ResponseMessage;
import com.example.be_study.service.user.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserLoginService {

    public final UserRepository userRepository;

    public UserLoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     *  이메일 중복 확인
     */
    public void isAlreadyExistEmail(String userEmail) {
        if (userRepository.findByUserEmail(userEmail).isPresent()) {
            throw new BadRequestApiException(ResponseMessage.ALREADY_EXIST_EMAIL);
        }
    }

    /**
     *  닉네임 중복 확인
     */
    public void isAlreadyExistNickname(String userNickname) {
        if (userRepository.findByUserNickName(userNickname).isPresent()) {
            throw new BadRequestApiException(ResponseMessage.ALREADY_EXIST_NICKNAME);
        }
    }
}
