package com.example.be_study.user.login

import com.example.be_study.service.user.domain.UserRepository
import com.example.be_study.service.user.service.UserSignUpService
import spock.lang.Specification
import spock.lang.Subject

abstract class UserSignUpServiceTest extends Specification {

    @Subject
    UserSignUpService userLoginService

    UserRepository userRepository = Mock()

    def setup() {
        userLoginService = new UserSignUpService(
                userRepository
        )
    }
}
