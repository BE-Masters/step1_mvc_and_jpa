package com.example.be_study.controller.user;

import com.example.be_study.common.response.DataResponse;
import com.example.be_study.service.user.domain.User;
import com.example.be_study.service.user.enums.OauthResponseCode;
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
        return new DataResponse<>(OauthResponseCode.OAUTH_AUTHORIZE_SUCCESS, redirectUrl);
    }

    @GetMapping("/login/{oauthServerType}/{code}")
    public DataResponse<User> login(
            @PathVariable("oauthServerType") OauthServerType oauthServerType,
            @PathVariable("code") String code
    ) {
        User login = oauthService.login(oauthServerType, code);
        return new DataResponse<>(OauthResponseCode.LOGIN_SUCCESS,login);
    }

}
