package com.example.be_study.service.oauth;

import com.example.be_study.service.user.enums.OauthServerType;

public interface AuthCodeRequestUrlProvider {
    OauthServerType supportServer();
    String provide();
}
