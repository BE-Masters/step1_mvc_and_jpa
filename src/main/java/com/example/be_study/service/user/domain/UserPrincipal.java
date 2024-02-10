package com.example.be_study.service.user.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
public class UserPrincipal implements UserDetails {

    private User user;
    private List<GrantedAuthority> authorities = new ArrayList<>();
    private final String ROLE_PRE_FIX = "ROLE_"; //권한 앞에 무조건 붙여야 권한 체크가 가능해집니다

    public UserPrincipal(User user) {
        this.user = user;
        authorities.add(new SimpleGrantedAuthority(ROLE_PRE_FIX + user.getUserType().toString()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getUserPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserNickName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return this.user.getDormancy();
    }

}
