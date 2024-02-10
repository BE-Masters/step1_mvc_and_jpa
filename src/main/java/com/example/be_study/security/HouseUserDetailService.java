package com.example.be_study.security;

import com.example.be_study.service.user.domain.User;
import com.example.be_study.service.user.domain.UserPrincipal;
import com.example.be_study.service.user.enums.UserResponseMessage;
import com.example.be_study.service.user.exception.UserBadRequestApiException;
import com.example.be_study.service.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class HouseUserDetailService implements UserDetailsService {

    public UserRepository userRepository;

    public HouseUserDetailService(UserRepository userRepository) {
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
    public UserDetails loadUserBy(Claims claims) {
        Long userId = Long.parseLong(claims.getSubject());
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new UserBadRequestApiException(UserResponseMessage.NOT_FOUND_USER));

        return new UserPrincipal(user);
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> {
                    log.error(id.toString());
                    return new UsernameNotFoundException("User not found with userId : " + id);
                });

        return new UserPrincipal(user);
    }
}
