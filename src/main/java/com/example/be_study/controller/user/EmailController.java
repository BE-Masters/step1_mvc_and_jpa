package com.example.be_study.controller.user;

import com.example.be_study.service.user.service.EmailServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mail")
public class EmailController {

    private final EmailServiceImpl emailService;

    public EmailController(EmailServiceImpl emailService) {
        this.emailService = emailService;
    }

    @GetMapping(name = "이메일 전송")
    @ResponseBody
    public ResponseEntity sendMail(@RequestParam("receiver") String receiver) throws Exception {
        emailService.sendMail(receiver);
        return new ResponseEntity(HttpStatus.OK);
    }

//    @GetMapping(value = "/auth", name = "이메일 인증 코드 확인")
//    @ResponseBody
//    public ResponseEntity checkMailCode(@RequestParam("receiver") String receiver, @RequestParam("code") String code) throws Exception {
//        boolean authCode = emailService.verifyEmailCode(receiver, code);
//        if (!authCode) {
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity(HttpStatus.OK);
//    }

}
