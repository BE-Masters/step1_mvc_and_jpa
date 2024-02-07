package com.example.be_study.service.user.service;

import com.example.be_study.common.jwt.JwtResponseMessage;
import com.example.be_study.common.jwt.JwtTokenUtil;
import com.example.be_study.common.jwt.TokenType;
import com.example.be_study.common.response.DataResponse;
import com.example.be_study.common.response.DataResponseCode;
import com.example.be_study.service.policy.service.PolicyAgreeService;
import com.example.be_study.service.user.domain.User;
import com.example.be_study.service.user.dto.UserRefreshAccessTokenResponse;
import com.example.be_study.service.user.dto.UserRefreshTokenRequest;
import com.example.be_study.service.user.dto.UserSignUpRequest;
import com.example.be_study.service.user.dto.UserSignUpResponse;
import com.example.be_study.service.user.enums.ProviderType;
import com.example.be_study.service.user.enums.UserResponseMessage;
import com.example.be_study.service.user.enums.UserSignUpResponseCode;
import com.example.be_study.service.user.exception.NotFoundUserException;
import com.example.be_study.service.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenUtil jwtTokenUtil;

    private final PolicyAgreeService policyAgreeService;

    public UserSignUpService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil, PolicyAgreeService policyAgreeService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
        this.policyAgreeService = policyAgreeService;
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
    public DataResponse<String> userIsAlreadyExistNickname(String userNickname) {
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
                    return new DataResponse<>(UserSignUpResponseCode.ALREADY_EXIST_NICKNAME, errorMessage);
                }
            }
        } else {
            return new DataResponse<>(UserSignUpResponseCode.SUCCESS);
        }
    }

    /**
     *  회원가입
     */
    @Transactional(readOnly = false)
    public DataResponse<UserSignUpResponse> userSignUp(UserSignUpRequest request) {
        User user = userRepository.save(User.ofOrigin(request.getUserEmail(), passwordEncoder, request.getUserPassword(), request.getUserNickname()));

        policyAgreeService.saveSignUpPolicy(user.getId(), request.getPolicyTypeList()); // 약관 동의

        String accessToken = jwtTokenUtil.createToken(user, TokenType.AccessToken);
        String refreshToken = jwtTokenUtil.createToken(user, TokenType.RefreshToken);

        user.updateRefreshToken(refreshToken); // refreshToken 저장

        return new DataResponse<>(UserSignUpResponseCode.SUCCESS, UserSignUpResponse.of(user, accessToken));
    }

    /**
     *  AccessToken 재발급
     */
    @Transactional(readOnly = false)
    public DataResponse<UserRefreshAccessTokenResponse> refreshAccessToken(UserRefreshTokenRequest request) {
        if (jwtTokenUtil.isExpired(request.getRefreshToken(), TokenType.RefreshToken)) {
            return new DataResponse<>(JwtResponseMessage.TOKEN_EXPIRED_MESSAGE);
        }

        Long userId = jwtTokenUtil.getUserId(request.getRefreshToken(), TokenType.RefreshToken);
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundUserException(UserResponseMessage.NOT_FOUND_USER));

        if (!user.getRefreshToken().equals(request.getRefreshToken())){
            return new DataResponse<>(JwtResponseMessage.TOKEN_PERMISSION_ERROR_MESSAGE);
        }

        String accessToken = jwtTokenUtil.createToken(user, TokenType.AccessToken);
        return new DataResponse<>(UserSignUpResponseCode.SUCCESS, UserRefreshAccessTokenResponse.of(accessToken));
    }
}
