package com.example.be_study.controller.user;

import com.example.be_study.common.response.DataResponse;
import com.example.be_study.service.user.domain.User;
import com.example.be_study.service.user.enums.OauthServerType;
import com.example.be_study.service.user.service.OauthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/oauth")
@RestController
public class OauthController {
    private final OauthService oauthService;

    @SneakyThrows
    @GetMapping("/{oauthServerType}")
    ResponseEntity<Void> redirectAuthCodeRequestUrl(
            @PathVariable OauthServerType oauthServerType,
            HttpServletResponse response
    ) {
        String redirectUrl = oauthService.getAuthCodeRequestUrl(oauthServerType);
        response.sendRedirect(redirectUrl);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/login/{oauthServerType}/{code}")
    public DataResponse<User> login(
            @PathVariable("oauthServerType") OauthServerType oauthServerType,
            @PathVariable("code") String code
    ) {
        return new DataResponse<>(200, "success", oauthService.login(oauthServerType, code));
    }

}
