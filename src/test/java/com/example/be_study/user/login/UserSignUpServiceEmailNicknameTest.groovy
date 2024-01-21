package com.example.be_study.user.login

import com.example.be_study.common.error.exception.BadRequestApiException
import com.example.be_study.service.user.domain.User
import com.example.be_study.service.user.enums.ProviderType
import com.example.be_study.service.user.enums.UserSignUpResponseCode

class UserSignUpServiceEmailNicknameTest extends UserSignUpServiceTest {

    def "사용 가능한 이메일"() {
        given:
        String userEmail = "test@naver.com"

        when:
        1 * userRepository.findByUserEmail(userEmail) >> Optional.empty()
        userSignUpService.userIsAlreadyExistEmail(userEmail)

        then:
        notThrown(BadRequestApiException)
    }

    def "이미 가입된 이메일"() {
        given:
        String userEmail = "test@naver.com"

        and:
        1 * userRepository.findByUserEmail(userEmail) >> Optional.of(new User(providerType: ProviderType.ORIGIN))

        when:
        def result = userSignUpService.userIsAlreadyExistEmail(userEmail)

        then:
        result.status == UserSignUpResponseCode.ALREADY_EXIST_ORIGIN_EMAIL.getResponseStatus()
    }

    def "이메일에 공백 사용"() {
        given:
        String userEmail = "te st@naver.com"

        when:
        def result = userSignUpService.userIsAlreadyExistEmail(userEmail)

        then:
        result.status == UserSignUpResponseCode.NOT_ALLOW_BLANK.getResponseStatus()
    }

    def "사용 가능한 별명"() {
        given:
        String userNickname = "홍길동"
        1 * userRepository.findAllByUserNickNameStartsWith(userNickname) >> []

        when:
        def result = userSignUpService.userIsAlreadyExistNickname(userNickname)

        then:
        notThrown(BadRequestApiException)
        result.status == UserSignUpResponseCode.SUCCESS.getResponseStatus()
    }

    def "별명에 공백 사용"() {
        given:
        String userNickname = "홍 길동"

        when:
        def result = userSignUpService.userIsAlreadyExistNickname(userNickname)

        then:
        result.status == UserSignUpResponseCode.NOT_ALLOW_BLANK.getResponseStatus()
    }
}
