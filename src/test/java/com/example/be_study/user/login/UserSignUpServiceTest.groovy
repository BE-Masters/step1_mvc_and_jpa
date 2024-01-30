package com.example.be_study.user.login

import com.example.be_study.service.user.repository.UserRepository
import com.example.be_study.service.user.service.UserSignUpService
import spock.lang.Specification
import spock.lang.Subject

abstract class UserSignUpServiceTest extends Specification {

    @Subject
    UserSignUpService userSignUpService

    UserRepository userRepository = Mock()

    def setup() {
        userSignUpService = new UserSignUpService(
                userRepository
        )
    }
}
