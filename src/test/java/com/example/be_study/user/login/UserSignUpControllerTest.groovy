package com.example.be_study.user.login


import com.example.be_study.controller.user.UserSignUpController
import com.example.be_study.service.user.service.UserSignUpService
import spock.lang.Specification
import spock.lang.Subject

abstract class UserSignUpControllerTest extends Specification {

    @Subject
    UserSignUpController userSignUpController

    UserSignUpService userSignUpService = Mock()

    def setup() {
        userSignUpController = new UserSignUpController(
                userSignUpService
        )
    }
}
