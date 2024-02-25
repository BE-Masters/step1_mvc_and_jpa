package com.example.be_study.policy.agree

import com.example.be_study.common.response.DataResponse
import com.example.be_study.service.policy.domain.PolicyAgree
import com.example.be_study.service.policy.domain.PolicyHistory
import com.example.be_study.service.policy.domain.QPolicyHistory
import com.example.be_study.service.policy.dto.PolicyNeedAgreeResponseDto
import com.example.be_study.service.policy.enums.PolicyAgreeResponseCode
import com.example.be_study.service.policy.enums.PolicyType
import com.querydsl.jpa.impl.JPAQuery
import org.springframework.util.ObjectUtils

class PolicyAgreeServiceNeedAgreeUserTest extends PolicyAgreeServiceTest {

    JPAQuery step1 = Mock(JPAQuery)
    JPAQuery step2 = Mock(JPAQuery)
    JPAQuery step3 = Mock(JPAQuery)

    def "유저가 가진 이용약관의 상태가 전부 최신이라면 이용약관 동의여부가 거짓 이여야합니다"() {
        given:
        def policyHistoryList = [new PolicyHistory(policyType: PolicyType.REQUIRED_IS_OVER_FOURTEEN, isLatestRevision: true),
                                 new PolicyHistory(policyType: PolicyType.REQUIRED_PERSONAL_INFO_PROCESS, isLatestRevision: true),
                                 new PolicyHistory(policyType: PolicyType.REQUIRED_TERMS_OF_SERVICE, isLatestRevision: true)]
        policyAgreeRepository.findAllByUserIdAndPolicyTypeNotIn(1L, _) >> { Long userId, List<PolicyType> policyTypeList ->
            return [] as List<PolicyAgree>
        }
        jpaQueryFactory.selectFrom(QPolicyHistory.policyHistory) >> step1
        step1.where(_) >> step2
        step2.where(_) >> step3
        step3.fetch() >> policyHistoryList

        when:
        DataResponse<PolicyNeedAgreeResponseDto> result = policyAgreeService
                .isNeedAgreeUser(1L)

        def needPolicyTypeEmpty = ObjectUtils.isEmpty(result.getResponse().needPolicyType)
        def alreadyAgreeMessage = PolicyAgreeResponseCode.ALREADY_AGREED.getResponseMessage() == result.getMessage()

        then:
        needPolicyTypeEmpty && alreadyAgreeMessage
    }

    def "user가 가진 이용약관의 상태가 하나라도 최신이 아니면 이용약관 동의 필요가 true이여야합니다."() {
        given:
        def policyHistoryList = [new PolicyHistory(policyType: PolicyType.REQUIRED_IS_OVER_FOURTEEN, isLatestRevision: true),
                                 new PolicyHistory(policyType: PolicyType.REQUIRED_PERSONAL_INFO_PROCESS, isLatestRevision: true),
                                 new PolicyHistory(policyType: PolicyType.REQUIRED_TERMS_OF_SERVICE, isLatestRevision: false)]
        policyAgreeRepository.findAllByUserIdAndPolicyHistoryIdIn(_, _) >> { Long userId, List<Long> policyHistoryIds ->
            return [
                    new PolicyAgree(policyHistoryId: 3, policyType: PolicyType.REQUIRED_TERMS_OF_SERVICE),
            ] as List<PolicyAgree>
        }
        jpaQueryFactory.selectFrom(QPolicyHistory.policyHistory) >> step1
        step1.where(_) >> step2
        step2.where(_) >> step3
        step3.fetch() >> policyHistoryList

        when:
        DataResponse<PolicyNeedAgreeResponseDto> result = policyAgreeService
                .isNeedAgreeUser(1L)

        def hasNeedPolicyType = !ObjectUtils.isEmpty(result.getResponse().needPolicyType)
        def needToAgreeMessage = PolicyAgreeResponseCode.NEED_TO_AGREED.getResponseMessage() == result.getMessage()

        then:
        hasNeedPolicyType && needToAgreeMessage
    }


}
