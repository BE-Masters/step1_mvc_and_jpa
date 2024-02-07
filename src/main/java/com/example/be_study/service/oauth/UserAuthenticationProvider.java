package com.example.be_study.service.oauth;

import com.example.be_study.service.user.enums.ProviderType;
import com.example.be_study.service.user.repository.UserPrincipal;
import com.example.be_study.service.user.service.UserDetailService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailService userDetailService;

    private final PasswordEncoder passwordEncoder;

    public UserAuthenticationProvider(@Lazy PasswordEncoder passwordEncoder, UserDetailService userDetailService){
        this.passwordEncoder = passwordEncoder;
        this.userDetailService = userDetailService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userNickName = authentication.getName();
        String userPassword = authentication.getCredentials().toString();

        UserDetails userDetails = userDetailService.loadUserByUsername(userNickName);

        if (userDetails instanceof UserPrincipal){
            UserPrincipal userPrincipal = (UserPrincipal) userDetails;

            if (userPrincipal.getProviderType() == ProviderType.ORIGIN && !passwordEncoder.matches(userPassword, userDetails.getPassword())){
                throw new BadCredentialsException("비밀번호가일치하지 않습니다");
            }
        }

        return new UsernamePasswordAuthenticationToken(userDetails, userPassword, userDetails.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
