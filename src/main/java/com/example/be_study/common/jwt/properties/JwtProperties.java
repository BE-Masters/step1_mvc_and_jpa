package com.example.be_study.common.jwt.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String accessTokenKey;

    private String refreshTokenKey;

}
