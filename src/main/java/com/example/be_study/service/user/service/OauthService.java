package com.example.be_study.service.user.service;

import com.example.be_study.service.user.domain.User;
import com.example.be_study.service.user.enums.OauthServerType;
import com.example.be_study.service.oauth.AuthCodeRequestUrlProviderComposite;
import com.example.be_study.service.oauth.OauthMemberClientComposite;
import org.springframework.stereotype.Service;

@Service
public class OauthService {
    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
    private final OauthMemberClientComposite oauthMemberClientComposite;
    private final UserService userService;

    public OauthService(AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite, OauthMemberClientComposite oauthMemberClientComposite, UserService userService) {
        this.authCodeRequestUrlProviderComposite = authCodeRequestUrlProviderComposite;
        this.oauthMemberClientComposite = oauthMemberClientComposite;
        this.userService = userService;
    }
    public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
        return authCodeRequestUrlProviderComposite.provide(oauthServerType);
    }

    public User login(OauthServerType oauthServerType, String authCode) {
        User user = oauthMemberClientComposite.requestAuthorizationAndAccessToken(oauthServerType, authCode);
        return userService.saveUser(user);
    }
}
