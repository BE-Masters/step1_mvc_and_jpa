package com.example.be_study.controller.user;

import com.example.be_study.common.response.DataResponse;
import com.example.be_study.common.response.DataResponseCode;
import com.example.be_study.service.user.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/v1/mail")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping(name = "이메일 전송")
    public DataResponse<DataResponseCode> sendMail(@RequestParam("receiver") String receiver) throws MessagingException, UnsupportedEncodingException {
        DataResponse<DataResponseCode> response = emailService.sendMail(receiver);
        return response;
    }

//    @GetMapping(value = "/auth", name = "이메일 인증 코드 확인")
//    public ResponseEntity checkMailCode(@RequestParam("receiver") String receiver, @RequestParam("code") String code) throws Exception {
//        boolean authCode = emailService.verifyEmailCode(receiver, code);
//        if (!authCode) {
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity(HttpStatus.OK);
//    }

}
