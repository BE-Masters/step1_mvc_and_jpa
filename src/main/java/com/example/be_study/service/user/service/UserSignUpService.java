package com.example.be_study.service.user.service;

import com.example.be_study.common.response.DataResponse;
import com.example.be_study.common.response.DataResponseCode;
import com.example.be_study.service.user.domain.UserRepository;
import com.example.be_study.service.user.enums.UserSignUpResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserSignUpService {

    public final UserRepository userRepository;

    public UserSignUpService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     *  이메일 중복 확인
     */
    @Transactional(readOnly = true)
    public DataResponse<DataResponseCode> userIsAlreadyExistEmail(String userEmail) {
        if (userRepository.findByUserEmail(userEmail).isPresent()) {
            return new DataResponse<>(UserSignUpResponseCode.ALREADY_EXIST_EMAIL);
        }

        return new DataResponse<>(UserSignUpResponseCode.SUCCESS);
    }

    /**
     *  닉네임 중복 확인
     */
    public DataResponse<DataResponseCode> userIsAlreadyExistNickname(String userNickname) {
        if (userRepository.findByUserNickName(userNickname).isPresent()) {
            return new DataResponse<>(UserSignUpResponseCode.ALREADY_EXIST_NICKNAME);
        }

        return new DataResponse<>(UserSignUpResponseCode.SUCCESS);
    }
}
