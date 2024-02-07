package com.example.be_study.controller.user;

import com.example.be_study.security.CurrentUser;
import com.example.be_study.service.user.dto.UserPrincipalResponse;
import com.example.be_study.service.user.repository.UserPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserPrincipalTestController {

    @GetMapping("/info")
    public UserPrincipalResponse getUserInfo(@CurrentUser UserPrincipal userPrincipal){
        return new UserPrincipalResponse(userPrincipal.getUserId(), userPrincipal.getUserNickName(), userPrincipal.getUserEmail());

    }

}
