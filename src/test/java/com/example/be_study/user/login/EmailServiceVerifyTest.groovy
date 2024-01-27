package com.example.be_study.user.login

import com.example.be_study.service.user.enums.UserSignUpResponseCode

class EmailServiceVerifyTest extends EmailServiceTest {

    def "메일 인증 코드 - 인증 성공"() {
        given:
        String email = "test@example.com"
        String code = "123456"

        and:
        1 * redisService.getData(email) >> code

        when:
        def result = emailService.verifyEmailCode(email, code)

        then:
        result.message == UserSignUpResponseCode.SUCCESS.getResponseMessage()
    }

    def "메일 인증 코드 - 인증 실패(올바르지 않은 인증 코드)"() {
        given:
        String email = "test@example.com"
        String code = "123456"
        String invalidCode = "654321"

        and:
        1 * redisService.getData(email) >> code

        when:
        def result = emailService.verifyEmailCode(email, invalidCode)

        then:
        result.message == UserSignUpResponseCode.INVALID_AUTH_MAIL_CODE.getResponseMessage()
    }

    def "메일 인증 코드 - 인증 실패(만료된 인증 코드)"() {
        given:
        String email = "test@example.com"
        String code = "123456"

        and:
        1 * redisService.getData(email) >> null

        when:
        def result = emailService.verifyEmailCode(email, code)

        then:
        result.message == UserSignUpResponseCode.EXPIRED_AUTH_MAIL_CODE.getResponseMessage()
    }

}
