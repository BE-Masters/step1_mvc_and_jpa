package com.example.be_study.user.login

import com.example.be_study.common.error.exception.BadRequestApiException
import com.example.be_study.common.response.ResponseMessage
import com.example.be_study.service.user.domain.User

class UserLoginServiceNicknameTest extends UserLoginServiceTest {

    def "사용 가능한 이메일"() {
        given:
        String userEmail = "test@naver.com"

        when:
        1 * userRepository.findByUserEmail(userEmail) >> Optional.empty()
        userLoginService.isAlreadyExistEmail(userEmail)

        then:
        notThrown(BadRequestApiException)
    }

    def "가입된 이메일"() {
        given:
        String userEmail = "test@naver.com"

        when:
        1 * userRepository.findByUserEmail(userEmail) >> Optional.of(new User())
        userLoginService.isAlreadyExistEmail(userEmail)

        then:
        def exception = thrown(BadRequestApiException)
        exception.responseMessage == ResponseMessage.ALREADY_EXIST_EMAIL
    }

    def "사용 가능한 별명"() {
        given:
        String userNickname = "홍길동"

        when:
        1 * userRepository.findByUserNickName(userNickname) >> Optional.empty()
        userLoginService.isAlreadyExistNickname(userNickname)

        then:
        notThrown(BadRequestApiException)
    }

    def "사용 중인 별명"() {
        given:
        String userNickname = "홍길동"

        when:
        1 * userRepository.findByUserNickName(userNickname) >> Optional.of(new User())
        userLoginService.isAlreadyExistNickname(userNickname)

        then:
        def exception = thrown(BadRequestApiException)
        exception.responseMessage == ResponseMessage.ALREADY_EXIST_NICKNAME
    }
}
