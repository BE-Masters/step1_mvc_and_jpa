package com.example.be_study.policy.analytics

import com.example.be_study.service.policy.domain.PolicyAgree
import com.example.be_study.service.policy.domain.PolicyAgreeRepository
import com.example.be_study.service.policy.domain.PolicyHistory
import com.example.be_study.service.policy.domain.PolicyHistoryRepository
import com.example.be_study.service.policy.enums.PolicyType
import com.example.be_study.service.policy.service.PolicyAgreeService
import com.example.be_study.service.policy.service.PolicyAnalyticsService
import spock.lang.Specification
import spock.lang.Subject

import java.util.stream.Collectors

class PolicyAnalyticsServiceTest extends Specification {

    @Subject
    PolicyAnalyticsService policyAnalyticsService

    PolicyAgreeRepository policyAgreeRepository = Mock()

    def setup() {
        policyAnalyticsService = new PolicyAnalyticsService(policyAgreeRepository)
    }

    //이용약관 동의 처리
    def "선택 약관을 동의한 유저의 정보를 추출합니다."() {
        when: "1번 유저의 약관을 저장합니다."
        policyAnalyticsService.getOptionAgreeUserInfo()
        then: "선택약관 목록 2개와 필수 약관 3개가 잘 저장됬는지 확인합니다."
    }

}
