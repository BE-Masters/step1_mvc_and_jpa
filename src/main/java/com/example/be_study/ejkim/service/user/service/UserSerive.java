package com.example.be_study.ejkim.service.user.service;

import com.example.be_study.ejkim.service.user.dto.UserRequestDto;
import com.example.be_study.ejkim.service.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Transactional(readOnly = true)
@Service
public class UserSerive {
    private final UserRepository userRepository;

    public UserSerive(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRequestDto getUserName(Long userId) {
        Optional<User> userOp = userRepository.findById(userId);
        if (userOp.isEmpty()) {
            throw new UserException(UserErrorType.NOT_USER);
        }

        User user = userOp.get();
        String userName = user.getName();
        return new UserRequestDto(userName);
    }

}
