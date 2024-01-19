package com.example.be_study.user.login

import com.example.be_study.service.user.domain.UserRepository
import com.example.be_study.service.user.service.UserLoginService
import spock.lang.Specification
import spock.lang.Subject

abstract class UserLoginServiceTest extends Specification {

    @Subject
    UserLoginService userLoginService

    UserRepository userRepository = Mock()

    def setup() {
        userLoginService = new UserLoginService(
                userRepository
        )
    }
}
