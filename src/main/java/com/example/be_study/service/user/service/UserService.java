package com.example.be_study.service.user.service;

import com.example.be_study.service.user.domain.User;
import com.example.be_study.service.user.domain.UserPrincipal;
import com.example.be_study.service.user.enums.UserResponseMessage;
import com.example.be_study.service.user.exception.UserBadRequestApiException;
import com.example.be_study.service.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class UserService {

    public UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<User> findAllByUserIdIn(Collection<Long> userIds) {
        return userRepository.findAllByIdIn(userIds);
    }

    public User saveUser(User user) {
        return userRepository
                .findByProviderKey(user.getProviderKey()).orElseGet(() -> userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public UserDetails loadUserBy(Long userId, Claims claims) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new UserBadRequestApiException(UserResponseMessage.NOT_FOUND_USER));

        return new UserPrincipal(user, claims);
    }


}
