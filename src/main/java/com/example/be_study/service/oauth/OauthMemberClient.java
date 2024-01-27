package com.example.be_study.service.oauth;

import com.example.be_study.service.user.domain.User;
import com.example.be_study.service.user.enums.OauthServerType;

public interface OauthMemberClient {

    OauthServerType supportServer();

    User fetch(String code);
}
