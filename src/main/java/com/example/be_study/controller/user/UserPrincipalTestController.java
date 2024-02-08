package com.example.be_study.controller.user;

import com.example.be_study.common.response.DataResponse;
import com.example.be_study.common.response.DataResponseCode;
import com.example.be_study.security.CurrentUser;
import com.example.be_study.service.user.dto.UserPrincipalResponse;
import com.example.be_study.service.user.enums.OauthResponseCode;
import com.example.be_study.service.user.enums.UserResponseMessage;
import com.example.be_study.service.user.repository.AdminOnly;
import com.example.be_study.service.user.repository.UserPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserPrincipalTestController {

    @AdminOnly
    @GetMapping("/info")
    public DataResponse<UserPrincipalResponse> getUserInfo(@CurrentUser UserPrincipal userPrincipal){
        UserPrincipalResponse response = UserPrincipalResponse.builder()
                .userId(userPrincipal.getUserId())
                .userNickName(userPrincipal.getUserNickName())
                .userEmail(userPrincipal.getUserEmail())
                .build();
        return new DataResponse<>(OauthResponseCode.OAUTH_AUTHORIZE_SUCCESS, response);
    }

}
