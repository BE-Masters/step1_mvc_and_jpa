package com.example.be_study.policy.agree

import com.example.be_study.service.policy.domain.PolicyAgree
import com.example.be_study.service.policy.domain.PolicyAgreeRepository
import com.example.be_study.service.policy.enums.SignUpPolicyType
import com.example.be_study.service.policy.service.PolicyAgreeService
import org.mockito.Mock
import spock.lang.Specification


class PolicyAgreeServiceTest extends Specification {

    @Mock
    PolicyAgreeRepository policyAgreeRepository
    PolicyAgreeService policyAgreeService

    def setup() {
        policyAgreeService = new PolicyAgreeService(policyAgreeRepository)
    }

    //이용약관 동의 처리
    def "회원가입용_선택약관2개_필수약관1개_저장되어야합니다."() {
        given: "선택약관 리스트를 생성합니다."
        List<SignUpPolicyType> signUpPolicyTypes = Arrays.asList(SignUpPolicyType.OPTION_EVENT_MAIL_OR_SMS, SignUpPolicyType.OPTION_PERSONAL_INFO_MARKETING)

        when: "선택약관 목록을 저장합니다."
        List<PolicyAgree> policyAgree = policyAgreeService.saveSignUpPolicy(1L, signUpPolicyTypes)

        then: "선택약관 목록 2개와 필수 약관 3개가 잘 저장됬는지 확인합니다."
        def successListSize = policyAgree.size() == 5
        successListSize
    }

//    def "회원가입용_선택약관1개_필수약관1개가 저장되어야합니다"() {
//
//    }
//
//    def "회원가입용_필수약관1개가 저장되어야합니다."() {
//
//    }
//
//    def "회원가입용_선택약관은 2개를 초과하면 안됩니다."() {
//
//    }
//
//    def "선택약관 리스트가 Null이면 400_INVALID_PARAMETER 를 반환합니다."() {
//
//    }

}
