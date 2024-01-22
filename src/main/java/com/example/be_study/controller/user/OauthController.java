package com.example.be_study.controller.user;

import com.example.be_study.common.response.DataResponse;
import com.example.be_study.service.user.domain.User;
import com.example.be_study.service.user.enums.KakaoOauthResponseCode;
import com.example.be_study.service.user.enums.OauthServerType;
import com.example.be_study.service.user.service.OauthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("/api/v1/oauth")
@RestController
public class OauthController {
    private final OauthService oauthService;

    public OauthController(OauthService oauthService) {
        this.oauthService = oauthService;
    }

    @GetMapping("/{oauthServerType}")
    public DataResponse<String> redirectAuthCodeRequestUrl(
            @PathVariable("oauthServerType") OauthServerType oauthServerType, HttpServletResponse response
    ) throws IOException {
        String redirectUrl = oauthService.getAuthCodeRequestUrl(oauthServerType);
        response.sendRedirect(redirectUrl);
        return new DataResponse<>(KakaoOauthResponseCode.OAUTH_AUTHORIZE_SUCCESS, "ok");
    }

    @GetMapping("/login/{oauthServerType}/{code}")
    public DataResponse<String> login(
            @PathVariable("oauthServerType") OauthServerType oauthServerType,
            @PathVariable("code") String code
    ) {
        return new DataResponse<>(KakaoOauthResponseCode.LOGIN_SUCCESS,"ok");
    }

}
