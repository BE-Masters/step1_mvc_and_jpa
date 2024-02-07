package com.example.be_study.service.user.repository;

import com.example.be_study.service.user.enums.ProviderType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserPrincipal implements UserDetails {
    private Long userId;
    private String userNickName;
    private String userEmail;
    private Collection<? extends GrantedAuthority> authorities;
    private ProviderType providerType;

    public UserPrincipal(Long userId, String userNickName, String userEmail, Collection<? extends GrantedAuthority> authorities, ProviderType providerType) {
        this.userId = userId;
        this.userNickName = userNickName;
        this.userEmail = userEmail;
        this.authorities = authorities;
        this.providerType = providerType;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public Long getUserId(){
        return userId;
    }

    public String getUserEmail(){
        return userEmail;
    }

    public String getUserNickName(){
        return userNickName;
    }

    public ProviderType getProviderType(){
        return providerType;
    }
}
