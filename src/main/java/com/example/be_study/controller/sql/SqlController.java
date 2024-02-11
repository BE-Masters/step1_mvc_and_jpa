package com.example.be_study.controller.sql;

import com.example.be_study.common.jwt.CurrentUser;
import com.example.be_study.service.user.domain.UserMetric;
import com.example.be_study.service.user.domain.UserPrincipal;
import com.example.be_study.service.user.service.UserMetricService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
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


    @GetMapping("/user-paging")
    public Page<UserMetric> pagingUserMetric(
            @PageableDefault(page = 1, size = 10) Pageable pageable,
            @CurrentUser UserPrincipal userPrincipal
    ) {
        return userService.pagingUserMetric(pageable,userPrincipal);
    }

    @GetMapping("/user-statistics-list")
    public String getUserStatisticsList() {
        return userService.getUserStatisticsList();
    }
}
