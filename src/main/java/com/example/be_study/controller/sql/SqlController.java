package com.example.be_study.controller.sql;

import com.example.be_study.service.user.service.UserMetricService;
import com.example.be_study.service.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 해당 컨트롤러는 실험적 컨트롤러입니다
 * SQL를 사용하고싶을때 사용합니다.
 */
@RestController
@RequestMapping("/api/v1/user/test")
public class SqlController {

    private final UserMetricService userService;

    public SqlController(UserMetricService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-users")
    public void createVeryManyUser() {

       userService.createVeryManyUser();
    }
}
