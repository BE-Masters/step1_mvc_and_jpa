package com.example.be_study.controller.user;

import com.example.be_study.common.response.DataResponse;
import com.example.be_study.common.response.DataResponseCode;
import com.example.be_study.service.user.dto.UserRefreshAccessTokenResponse;
import com.example.be_study.service.user.dto.UserRefreshTokenRequest;
import com.example.be_study.service.user.dto.UserSignUpRequest;
import com.example.be_study.service.user.dto.UserSignUpResponse;
import com.example.be_study.service.user.service.UserSignUpService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sign-up")
public class UserSignUpController {

    private final UserSignUpService userSignUpService;

    public UserSignUpController(UserSignUpService userSignUpService) {
        this.userSignUpService = userSignUpService;
    }

    /**
     *  이메일 중복 확인
     */
    @GetMapping(value = "/verify-email", name = "이메일 중복 확인")
    public DataResponse<DataResponseCode> userIsAlreadyExistEmail(@RequestParam(name = "userEmail") String userEmail) {
        DataResponse<DataResponseCode> response = userSignUpService.userIsAlreadyExistEmail(userEmail);
        return response;
    }

    /**
     *  닉네임 중복 확인
     */
    @GetMapping(value = "/verify-nickname", name = "닉네임 중복 확인")
    public DataResponse<String> userIsAlreadyExistNickname(@RequestParam(name = "userNickname") String userNickname) {
        DataResponse<String> response = userSignUpService.userIsAlreadyExistNickname(userNickname);
        return response;
    }

    /**
     *  회원가입
     */
    @PostMapping(name = "자체 회원가입")
    public DataResponse<UserSignUpResponse> userSignUp(@Valid @RequestBody UserSignUpRequest request) {
        DataResponse<UserSignUpResponse> response = userSignUpService.userSignUp(request);
        return response;
    }

    /**
     *  AccessToken 재발급
     */
    @PutMapping(name = "AccessToken 재발급")
    public DataResponse<UserRefreshAccessTokenResponse> refreshAccessToken(@RequestBody UserRefreshTokenRequest request) {
        DataResponse<UserRefreshAccessTokenResponse> response = userSignUpService.refreshAccessToken(request);
        return response;
    }
}
