package com.example.be_study.service.user.controller;

import com.example.be_study.common.response.DataResponse;
import com.example.be_study.common.response.DataResponseCode;
import com.example.be_study.service.user.service.UserSignUpService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public DataResponse<DataResponseCode> userIsAlreadyExistNickname(@RequestParam(name = "userNickname") String userNickname) {
        DataResponse<DataResponseCode> response = userSignUpService.userIsAlreadyExistNickname(userNickname);
        return response;
    }
}
