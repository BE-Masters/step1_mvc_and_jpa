package com.example.be_study.user.login

import com.example.be_study.common.jwt.TokenType
import com.example.be_study.service.user.domain.User
import com.example.be_study.service.user.dto.UserSignUpRequest
import com.example.be_study.service.user.enums.ProviderType
import com.example.be_study.service.user.enums.UserSignUpResponseCode
import com.example.be_study.service.user.enums.UserType

class UserSignUpServiceSignUpTest extends UserSignUpServiceTest {

    def "회원가입 성공"() {
        given:
        def request = new UserSignUpRequest(
                userEmail: "test@test.com",
                userPassword: "qwer1234",
                userNickname: "고냥이",
                policyTypeList: ["OPTION_PERSONAL_INFO_MARKETING"]
        )

        List<User> userList = new ArrayList<>()

        userRepository.findByUserEmail(request.userEmail) >> Optional.ofNullable(null)
        userRepository.findAllByUserNickNameStartsWith(request.userNickname) >> userList

        user = new User(
                id: 1,
                userEmail: "test@test.com",
                userPassword: "encoded",
                userNickName: "고냥이",
                providerType: ProviderType.ORIGIN,
                dormancy: false,
                userType: UserType.BASIC_USER
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

    def "회원가입 실패 - 이미 가입된 이메일"() {
        given:
        def request = new UserSignUpRequest(
                userEmail: "test@test.com",
                userPassword: "qwer1234",
                userNickname: "고냥이123",
                policyTypeList: ["OPTION_PERSONAL_INFO_MARKETING"]
        )

        user = new User(
                id: 1,
                userEmail: "test@test.com",
                userPassword: "encoded",
                userNickName: "고냥이",
                providerType: ProviderType.ORIGIN,
                dormancy: false,
                userType: UserType.BASIC_USER
        )

        userRepository.findByUserEmail(_) >> Optional.of(user)

        when:
        def result = userSignUpService.userSignUp(request)

        then:
        result.message == UserSignUpResponseCode.ALREADY_EXIST_ORIGIN_EMAIL.getResponseMessage()
    }
}
