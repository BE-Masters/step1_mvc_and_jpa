package com.example.be_study.yugyeong.controller;

import com.example.be_study.yugyeong.application.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *  Controller
 */
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping(value = "/profile", name = "사용자 프로필 조회") // '/profile' 경로로 들어온 HTTP GET 요청을 받는다.
    public String userProfile(Model model){
        model.addAttribute("profile", homeService.userProfile()); // View 에게 데이터 전달
        return "profile";
    }

}
