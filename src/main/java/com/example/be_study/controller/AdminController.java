package com.example.be_study.controller;

import com.example.be_study.common.jwt.CurrentUser;
import com.example.be_study.service.user.domain.UserPrincipal;
import io.lettuce.core.dynamic.annotation.Param;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @PreAuthorize("hasRole('BASIC_USER')")
    @GetMapping(value = "/type1")
    public String checkAdminUser(@CurrentUser UserPrincipal user) {
        log.info("/type1 실행중");
        return "일반 유저만 실행 후 결과를 받을 수 있습니다.";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/type2")
    public String hasRoleTest(@CurrentUser UserPrincipal user) {
        log.info("/type2 실행중");
        return "어드민 유저만 실행 후 결과를 받을 수 있습니다.";
    }

    @PostAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/type3")
    public String hasRoleTest2(@CurrentUser UserPrincipal user) {

        log.info("/type3 실행중");
        return "일반 유저만 실행 후 결과를 받을 수 있습니다.";
    }

    @Secured("ROLE_BASIC_USER")
    @GetMapping(value = "/type4")
    public String securedTest(@CurrentUser UserPrincipal user) {

        log.info("/type4 실행중");
        return "secured BASIC 유저만 실행 후 결과를 받을 수 있습니다.";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/type5")
    public String securedTest2(@CurrentUser UserPrincipal user) {

        log.info("/type5 실행중");
        return "secured BASIC 유저만 실행 후 결과를 받을 수 있습니다.";
    }

    //접근권한이 2개를 다 만족해야 통과 할수 있음
    @Secured("{ROLE_ADMIN, ROLE_BASIC_USER}")
    @GetMapping(value = "/type6")
    public String securedTest3(@CurrentUser UserPrincipal user) {

        log.info("/type6 실행중");
        return "secured BASIC, ADMIN 유저만 실행 후 결과를 받을 수 있습니다.";
    }

    @PreAuthorize("hasRole('ROLE_BASIC_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/type7")
    public String spelTest(@CurrentUser UserPrincipal user) {
        log.info("/type7 실행중");
        return "BASIC, ADMIN 둘중 하나의 유저라도 사용 가능합니다.";
    }

    @PreAuthorize("@UserCheckUtils.isAdmin()")
    @GetMapping(value = "/type8")
    public String spelTest2(@CurrentUser UserPrincipal userPrincipal) {
        log.info("/type8 실행중");
        return "어드민 유저를 SPEL 언어로 실행합니다.";
    }

}
