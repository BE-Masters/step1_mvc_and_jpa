package com.example.be_study.controller.admin;

import com.example.be_study.service.admin.annotation.AuthAdmin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @AuthAdmin
    @GetMapping(value = "/admin", name = "관리자 확인")
    public String isAdmin() {
        return "오늘의 집 관리자입니다.";
    }
}
