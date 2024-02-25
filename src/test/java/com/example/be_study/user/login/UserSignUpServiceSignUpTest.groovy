package com.example.be_study.user.login

import com.example.be_study.common.jwt.TokenType
import com.example.be_study.service.user.dto.UserSignUpRequest
import com.example.be_study.service.user.enums.UserSignUpResponseCode

class UserSignUpServiceSignUpTest extends UserSignUpServiceTest {

    def "회원가입 성공"() {
        given:
        def request = new UserSignUpRequest(
                userEmail: "test@test.com",
                userPassword: "qwer1234",
                userNickname: "고냥이",
                policyTypeList: ["OPTION_PERSONAL_INFO_MARKETING"]
        )

        userRepository.save(_) >> user

        policyAgreeService.saveSignUpPolicy(user.id, request.policyTypeList)

        jwtTokenUtil.createToken(user.id, user.userType, TokenType.AccessToken) >> "AccessToken"
        jwtTokenUtil.createToken(user.id, user.userType, TokenType.RefreshToken) >> "RefreshToken"

        when:
        def result = userSignUpService.userSignUp(request)

        then:
        result.getMessage() == UserSignUpResponseCode.SUCCESS.getResponseMessage()
    }
}
