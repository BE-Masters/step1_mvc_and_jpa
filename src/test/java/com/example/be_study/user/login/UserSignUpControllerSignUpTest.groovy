package com.example.be_study.user.login


import com.example.be_study.service.user.dto.UserSignUpRequest

class UserSignUpControllerSignUpTest extends UserSignUpControllerTest {

    def "회원가입 실패 - 비밀번호 8자 미만"() {
        given:
        def request = new UserSignUpRequest(
                userEmail: "existing@test.com",
                userPassword: "qwer",
                userNickname: "고냥이",
                policyTypeList: ["OPTION_PERSONAL_INFO_MARKETING"]
        )

        when:
        def result = userSignUpController.userSignUp(request)

        then:
        result.getStatus() == 417
        result.getMessage() == "비밀번호는 최소 8자 이상 입력해야 됩니다."
    }
}
