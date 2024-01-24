package com.example.be_study.service.user.service;

import com.example.be_study.common.response.DataResponse;
import com.example.be_study.common.response.DataResponseCode;
import com.example.be_study.service.user.domain.User;
import com.example.be_study.service.user.enums.ProviderType;
import com.example.be_study.service.user.enums.UserSignUpResponseCode;
import com.example.be_study.service.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if (userEmail == null || userEmail.equals("")){
            return new DataResponse<>(UserSignUpResponseCode.REQUIRED_FIELD);
        }

        if (userEmail.contains(" ")) { // 공백 검사 후 공백 제거
            userEmail = userEmail.replaceAll(" ","");
        }

        String validEmail = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"; // 이메일 정규식 정의
        Pattern pattern = Pattern.compile(validEmail); // 정의한 정규식을 패턴으로 컴파일
        Matcher matcher = pattern.matcher(userEmail); // Matcher 를 사용해서 userEmail 패턴 검사

        if (!matcher.matches()) {
            return new DataResponse<>(UserSignUpResponseCode.INVALID_EMAIL_FORMAT);
        }

        Optional<User> user = userRepository.findByUserEmail(userEmail);

        if (user.isPresent()) {
            ProviderType providerType = user.get().getProviderType();
            return switch (providerType) {
                case KAKAO -> new DataResponse<>(UserSignUpResponseCode.ALREADY_EXIST_KAKAO_EMAIL);
                case NAVER -> new DataResponse<>(UserSignUpResponseCode.ALREADY_EXIST_NAVER_EMAIL);
                case FACEBOOK -> new DataResponse<>(UserSignUpResponseCode.ALREADY_EXIST_FACEBOOK_EMAIL);
                default -> new DataResponse<>(UserSignUpResponseCode.ALREADY_EXIST_ORIGIN_EMAIL);
            };
        } else {
            return new DataResponse<>(UserSignUpResponseCode.SUCCESS);
        }
    }

    /**
     *  닉네임 중복 확인
     */
    @Transactional(readOnly = true)
    public DataResponse<DataResponseCode> userIsAlreadyExistNickname(String userNickname) {
        if (userNickname == null || userNickname.equals("")){
            return new DataResponse<>(UserSignUpResponseCode.REQUIRED_FIELD);
        }

        if (userNickname.contains(" ")) { // 공백 검사 후 공백 제거
            userNickname = userNickname.replaceAll(" ","");
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
        } else {
            return new DataResponse<>(UserSignUpResponseCode.SUCCESS);
        }
    }
}
