package com.example.be_study.service.user.service;

import com.example.be_study.common.response.DataResponse;
import com.example.be_study.common.response.DataResponseCode;
import com.example.be_study.service.user.domain.User;
import com.example.be_study.service.user.domain.UserRepository;
import com.example.be_study.service.user.enums.ProviderType;
import com.example.be_study.service.user.enums.UserSignUpResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        if (userEmail.contains(" ")) { // 공백 검사
            return new DataResponse<>(UserSignUpResponseCode.NOT_ALLOW_BLANK);
        }

        User user = userRepository.findByUserEmail(userEmail).orElse(null);

        if (user != null) {
            ProviderType providerType = user.getProviderType();
            return switch (providerType) {
                case KAKAO -> new DataResponse<>(UserSignUpResponseCode.ALREADY_EXIST_KAKAO_EMAIL);
                case NAVER -> new DataResponse<>(UserSignUpResponseCode.ALREADY_EXIST_NAVER_EMAIL);
                case FACEBOOK -> new DataResponse<>(UserSignUpResponseCode.ALREADY_EXIST_FACEBOOK_EMAIL);
                default -> new DataResponse<>(UserSignUpResponseCode.ALREADY_EXIST_ORIGIN_EMAIL);
            };
        }

        return new DataResponse<>(UserSignUpResponseCode.SUCCESS);
    }

    /**
     *  닉네임 중복 확인
     */
    @Transactional(readOnly = true)
    public DataResponse<DataResponseCode> userIsAlreadyExistNickname(String userNickname) {
        if (userNickname.contains(" ")) { // 공백 검사
            return new DataResponse<>(UserSignUpResponseCode.NOT_ALLOW_BLANK);
        }

        List<User> nicknameList = userRepository.findAllByUserNickNameStartsWith(userNickname);

        if (!nicknameList.isEmpty()) {
            while (true) {
                String recommendNickname = userNickname + String.format("%02d", (int) (Math.random() * 90) + 10); // 닉네임 추천
                if (nicknameList.stream().noneMatch(user -> user.getUserNickName().equals(recommendNickname))) {
                    String errorMessage = "'" + recommendNickname + "' 이 별명은 어떠신가요? 별명은 언제든 수정하실 수 있습니다.";
                    return new DataResponse<>(UserSignUpResponseCode.ALREADY_EXIST_NICKNAME.getResponseStatus(), errorMessage);
                }
            }
        }

        return new DataResponse<>(UserSignUpResponseCode.SUCCESS);
    }
}
