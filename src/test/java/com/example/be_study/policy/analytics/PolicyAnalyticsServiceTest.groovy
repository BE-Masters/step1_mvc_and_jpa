package com.example.be_study.policy.analytics


import com.example.be_study.service.policy.domain.PolicyAgreeRepository
import com.example.be_study.service.policy.service.PolicyAnalyticsService
import com.example.be_study.service.user.service.UserService
import spock.lang.Specification
import spock.lang.Subject

class PolicyAnalyticsServiceTest extends Specification {

    @Subject
    PolicyAnalyticsService policyAnalyticsService

    PolicyAgreeRepository policyAgreeRepository = Mock()
    UserService userService = Mock()

    def setup() {
        policyAnalyticsService = new PolicyAnalyticsService(policyAgreeRepository, userService)
    }

    //이용약관 동의 처리
    def "선택 약관을 동의한 유저가 없는 경우 유저 List가 Empty List로 반환합니다."() {
        expect:
        policyAnalyticsService.getOptionAgreeUserInfo().size() == 0
    }

}
