package com.example.be_study.service.user.service;

import com.example.be_study.service.user.UserPrincipal;
import com.example.be_study.service.user.domain.User;
import com.example.be_study.service.user.enums.UserResponseMessage;
import com.example.be_study.service.user.exception.NotFoundUserException;
import com.example.be_study.service.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findById(Long.parseLong(userId)).orElseThrow(() -> new NotFoundUserException(UserResponseMessage.NOT_FOUND_USER));
        return UserPrincipal.create(user);
    }
}
