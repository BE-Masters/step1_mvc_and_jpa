package com.example.be_study.controller.user;

import com.example.be_study.common.response.DataResponse;
import com.example.be_study.service.user.enums.UserSignUpResponseCode;
import com.example.be_study.service.user.service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mail")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

//    @GetMapping(name = "이메일 전송")
//    public DataResponse<UserSignUpResponseCode> sendMail(@RequestParam("receiver") String receiver) {
//        DataResponse<UserSignUpResponseCode> response = emailService.sendMail(receiver);
//        return response;
//    }
//
//    @GetMapping(value = "/auth", name = "이메일 인증 코드 확인")
//    public DataResponse<UserSignUpResponseCode> checkMailCode(@RequestParam("receiver") String receiver, @RequestParam("code") String code) {
//        DataResponse<UserSignUpResponseCode> response = emailService.verifyEmailCode(receiver, code);
//        return response;
//    }

}
