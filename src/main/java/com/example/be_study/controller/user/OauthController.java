package com.example.be_study.controller.user;

import com.example.be_study.common.jwt.JwtTokenUtil;
import com.example.be_study.common.jwt.TokenType;
import com.example.be_study.common.response.DataResponse;
import com.example.be_study.service.user.domain.User;
import com.example.be_study.service.user.domain.UserPrincipal;
import com.example.be_study.service.user.enums.OauthResponseCode;
import com.example.be_study.service.user.enums.OauthServerType;
import com.example.be_study.service.user.service.OauthService;
import com.example.be_study.utils.AdminCheck;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.stream.Collectors;

@RequestMapping("/api/v1/oauth")
@RestController
public class OauthController {
    private final OauthService oauthService;
    private final JwtTokenUtil jwtTokenUtil;

    public OauthController(OauthService oauthService, JwtTokenUtil jwtTokenUtil) {
        this.oauthService = oauthService;
        this.jwtTokenUtil = jwtTokenUtil;
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
        String accessToken = jwtTokenUtil.createToken(login, TokenType.AccessToken); //jwt access토큰 생성
        String refreshToken = jwtTokenUtil.createToken(login, TokenType.RefreshToken); //jwt refresh토큰 생성
        System.out.println("accessToken: Bearer "+accessToken);
        return new DataResponse<>(OauthResponseCode.LOGIN_SUCCESS,login);
    }

    @GetMapping("/check") // principal 데이터 가져오기 test
    public String roleCheck(Authentication authentication) {
        String result;
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream().map(r -> String.valueOf(r)).collect(Collectors.joining(","));
        if(role.equals("ADMIN")){
            result = "어드민입니다";
        }else{
            result = "유저입니다";
        }
        return result;
    }
}
