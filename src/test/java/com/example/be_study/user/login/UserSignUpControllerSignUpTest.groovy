package com.example.be_study.user.login

import com.example.be_study.service.user.dto.UserSignUpRequest
import com.google.gson.Gson
import org.assertj.core.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.setup.MockMvcBuilders

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

class UserSignUpControllerSignUpTest extends UserSignUpControllerTest {

    @Autowired
    MockMvc mockMvc

    def setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userSignUpController).build()
    }

    def "회원가입 실패 - 비밀번호 8자 미만"() {
        given:
        def request = new UserSignUpRequest(
                userEmail: "existing@test.com",
                userPassword: "qwer",
                userNickname: "고냥이",
                policyTypeList: ["OPTION_PERSONAL_INFO_MARKETING"]
        )

        userSignUpController.userSignUp(request)

        Gson gson = new Gson();

        when:
        MvcResult result = mockMvc.perform(post("/api/v1/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(request)))
                .andReturn();

        System.out.print("")
        then:
        Assertions.assertThat(HttpStatus.EXPECTATION_FAILED)
        Assertions.assertThat(result.getResolvedException().getMessage()).contains("비밀번호는 최소 8자 이상 입력해야 됩니다.")
    }

    def "회원가입 실패 - 이메일 미입력"() {
        given:
        def request = new UserSignUpRequest(
                userPassword: "qwer1234",
                userNickname: "고냥이",
                policyTypeList: ["OPTION_PERSONAL_INFO_MARKETING"]
        )

        userSignUpController.userSignUp(request)

        Gson gson = new Gson();

        when:
        MvcResult result = mockMvc.perform(post("/api/v1/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(request)))
                .andReturn();

        System.out.print("")
        then:
        Assertions.assertThat(HttpStatus.EXPECTATION_FAILED)
        Assertions.assertThat(result.getResolvedException().getMessage()).contains("이메일은 필수입니다.")
    }

    def "회원가입 실패 - 닉네임 2자 미만"() {
        given:
        def request = new UserSignUpRequest(
                userEmail: "existing@test.com",
                userPassword: "qwer1234",
                userNickname: "고",
                policyTypeList: ["OPTION_PERSONAL_INFO_MARKETING"]
        )

        userSignUpController.userSignUp(request)

        Gson gson = new Gson();

        when:
        MvcResult result = mockMvc.perform(post("/api/v1/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(request)))
                .andReturn();

        System.out.print("")
        then:
        Assertions.assertThat(HttpStatus.EXPECTATION_FAILED)
        Assertions.assertThat(result.getResolvedException().getMessage()).contains("닉네임은 최소 2자 이상 최대 15자까지 입력해야 됩니다.")
    }

    def "회원가입 실패 - 이용약관 Null"() {
        given:
        def request = new UserSignUpRequest(
                userEmail: "existing@test.com",
                userPassword: "qwer1234",
                userNickname: "고냥이",
                policyTypeList: null
        )

        userSignUpController.userSignUp(request)

        Gson gson = new Gson();

        when:
        MvcResult result = mockMvc.perform(post("/api/v1/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(request)))
                .andReturn();

        System.out.print("")
        then:
        Assertions.assertThat(HttpStatus.EXPECTATION_FAILED)
        Assertions.assertThat(result.getResolvedException().getMessage()).contains("약관 동의는 필수입니다.")
    }
}
