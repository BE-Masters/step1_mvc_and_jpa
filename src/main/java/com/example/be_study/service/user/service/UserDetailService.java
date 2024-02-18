package com.example.be_study.service.user.service;

import com.example.be_study.service.user.domain.User;
import com.example.be_study.service.user.enums.ProviderType;
import com.example.be_study.service.user.enums.UserType;
import com.example.be_study.service.user.repository.UserPrincipal;
import com.example.be_study.service.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    private ProviderType providerType;

    @Override
    public UserDetails loadUserByUsername(String userNickName) throws UsernameNotFoundException{
        User user = userRepository.findByUserNickName(userNickName)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with userNickName: " + userNickName));

        // 사용자의 권한을 기반으로 GrantedAuthority 목록 생성
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getUserType() == UserType.ADMIN) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return new UserPrincipal(user.getId(), user.getUserNickName(), user.getUserEmail(), authorities, providerType);
    }

    public UserPrincipal loadUserById(Long userId) throws UsernameNotFoundException{
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with userNickName: "));

        // 사용자의 권한을 기반으로 GrantedAuthority 목록 생성
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getUserType() == UserType.ADMIN) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return new UserPrincipal(user.getId(), user.getUserNickName(), user.getUserEmail(), authorities, providerType);
    }
}
