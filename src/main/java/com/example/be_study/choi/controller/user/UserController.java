package com.example.be_study.choi.controller.user;

import com.example.be_study.choi.utils.IdentityValidateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @GetMapping("/validate/identity/national")
    public boolean isIdentityNationalUser(){
        return IdentityValidateUtils.isIdentityNationalUser("9302251111111");
    }
}
