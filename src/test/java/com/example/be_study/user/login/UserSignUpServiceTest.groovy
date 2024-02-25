package com.example.be_study.user.login

import com.example.be_study.common.jwt.JwtTokenUtil
import com.example.be_study.service.policy.service.PolicyAgreeService
import com.example.be_study.service.user.domain.User
import com.example.be_study.service.user.repository.UserRepository
import com.example.be_study.service.user.service.UserSignUpService
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification
import spock.lang.Subject

abstract class UserSignUpServiceTest extends Specification {

    @Subject
    UserSignUpService userSignUpService

    UserRepository userRepository = Mock()

    PasswordEncoder passwordEncoder = Mock()

    JwtTokenUtil jwtTokenUtil = Mock()

    PolicyAgreeService policyAgreeService = Mock()

    User user = Mock(User)

    def setup() {
        userSignUpService = new UserSignUpService(
                userRepository, passwordEncoder, jwtTokenUtil, policyAgreeService
        )
        userRepository.save(_) >> user
    }
}
