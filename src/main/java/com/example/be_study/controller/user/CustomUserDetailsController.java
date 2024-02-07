package com.example.be_study.controller.user;

import com.example.be_study.service.user.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class CustomUserDetailsController {

    /**
     * 유저 정보 가져오기
     */
    @GetMapping(name = "유저 정보 가져오기")
    public String userPrincipal(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        String userName = userPrincipal.getUsername();
        return userName;
    }
}
