package com.example.be_study.controller;

import com.example.be_study.common.jwt.CurrentUser;
import com.example.be_study.service.user.domain.UserPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/api/v1/admin")
public class AdminController {

    @GetMapping( name = "관리자용 유저 확인 API")
    @PreAuthorize("@RoleUtils.isAdmin()")
    public String checkAdminUser(@CurrentUser UserPrincipal user) {
        return "어드민입니다";
    }

}
