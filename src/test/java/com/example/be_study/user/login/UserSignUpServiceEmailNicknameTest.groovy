package com.example.be_study.user.login

import com.example.be_study.service.user.exception.UserBadRequestApiException
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
        notThrown(UserBadRequestApiException)
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

    def "이메일 NULL 입력"() {
        given:
        String userEmail = ""

        when:
        def result = userSignUpService.userIsAlreadyExistEmail(userEmail)

        then:
        result.status == UserSignUpResponseCode.REQUIRED_FIELD.getResponseStatus()
    }

    def "올바르지 않은 이메일 형식 - '@' 없을 경우"() {
        given:
        def userEmail = "test.email"

        when:
        def result = userSignUpService.userIsAlreadyExistEmail(userEmail)

        then:
        result.status == UserSignUpResponseCode.INVALID_EMAIL_FORMAT.getResponseStatus()
    }

    def "올바르지 않은 이메일 형식 - '.' 없을 경우"() {
        given:
        def userEmail = "test@email"

        when:
        def result = userSignUpService.userIsAlreadyExistEmail(userEmail)

        then:
        result.status == UserSignUpResponseCode.INVALID_EMAIL_FORMAT.getResponseStatus()
    }

    def "사용 가능한 별명"() {
        given:
        String userNickname = "홍길동"
        1 * userRepository.findAllByUserNickNameStartsWith(userNickname) >> []

        when:
        def result = userSignUpService.userIsAlreadyExistNickname(userNickname)

        then:
        notThrown(UserBadRequestApiException)
        result.status == UserSignUpResponseCode.SUCCESS.getResponseStatus()
    }

    def "별명 NULL 입력"() {
        given:
        String userNickname = ""
        1 * userRepository.findAllByUserNickNameStartsWith(userNickname) >> []

        when:
        def result = userSignUpService.userIsAlreadyExistNickname(userNickname)

        then:
        result.status == UserSignUpResponseCode.REQUIRED_FIELD.getResponseStatus()
    }
}
