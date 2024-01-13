package com.example.be_study.policy.agree

import com.example.be_study.service.policy.domain.PolicyAgree
import com.example.be_study.service.policy.domain.PolicyAgreeRepository
import com.example.be_study.service.policy.domain.PolicyHistory
import com.example.be_study.service.policy.domain.PolicyHistoryRepository
import com.example.be_study.service.policy.enums.PolicyType
import com.example.be_study.service.policy.service.PolicyAgreeService
import spock.lang.Specification
import spock.lang.Subject

import java.util.stream.Collectors

class PolicyAgreeServiceTest extends Specification {

    @Subject
    PolicyAgreeService policyAgreeService

    PolicyAgreeRepository policyAgreeRepository = Mock()
    PolicyHistoryRepository policyHistoryRepository = Mock()

    def setup() {
        policyAgreeService = new PolicyAgreeService(policyAgreeRepository, policyHistoryRepository)
    }

    //이용약관 동의 처리
    def "회원가입용_선택약관2개_필수약관3개_총5개가 저장되어야합니다."() {
        given: "Agree, History Repository의 Stub을 생성합니다."
        List<PolicyType> signUpPolicyTypes = Arrays.asList(PolicyType.OPTION_EVENT_MAIL_OR_SMS, PolicyType.OPTION_PERSONAL_INFO_MARKETING)
        policyAgreeRepository.saveAll(_) >> [
                new PolicyAgree(policyHistoryId: 1, policyType: PolicyType.REQUIRED_IS_OVER_FOURTEEN),
                new PolicyAgree(policyHistoryId: 2, policyType: PolicyType.REQUIRED_PERSONAL_INFO_PROCESS),
                new PolicyAgree(policyHistoryId: 3, policyType: PolicyType.REQUIRED_TERMS_OF_SERVICE),
                new PolicyAgree(policyHistoryId: 4, policyType: PolicyType.OPTION_PERSONAL_INFO_MARKETING),
                new PolicyAgree(policyHistoryId: 5, policyType: PolicyType.OPTION_EVENT_MAIL_OR_SMS),
        ]
        policyHistoryRepository.findAllByPolicyTypeInAndIsLatestRevision(_, true) >> { List list, boolean revision ->
            return [
                    new PolicyHistory(policyType: PolicyType.REQUIRED_IS_OVER_FOURTEEN, isLatestRevision: true),
                    new PolicyHistory(policyType: PolicyType.REQUIRED_PERSONAL_INFO_PROCESS, isLatestRevision: true),
                    new PolicyHistory(policyType: PolicyType.REQUIRED_TERMS_OF_SERVICE, isLatestRevision: true),
                    new PolicyHistory(policyType: PolicyType.OPTION_PERSONAL_INFO_MARKETING, isLatestRevision: true),
                    new PolicyHistory(policyType: PolicyType.OPTION_EVENT_MAIL_OR_SMS, isLatestRevision: true),
            ] as List<PolicyHistory>
        }

        when: "1번 유저의 약관을 저장합니다."
        List<PolicyAgree> policyAgree = policyAgreeService.saveSignUpPolicy(1L, signUpPolicyTypes)
        def sortedList = PolicyType.SIGN_ALL_LIST.sort()
        def agreeSortedList = policyAgree.stream().map(it -> it.getPolicyType()).collect(Collectors.toList()).sort()
        def equalAllList = sortedList == agreeSortedList

        then: "선택약관 목록 2개와 필수 약관 3개가 잘 저장됬는지 확인합니다."
        policyAgree.size() == 5 && equalAllList
    }

    def "회원가입시 약관 History를 가져오고 약관 동의 이력을 저장합니다."() {
        given: "Agree, History Repository의 Stub을 생성합니다."
        List<PolicyType> signUpPolicyTypes = Arrays.asList(PolicyType.OPTION_EVENT_MAIL_OR_SMS)
        policyAgreeRepository.saveAll(_) >> [
                new PolicyAgree(policyHistoryId: 1, policyType: PolicyType.REQUIRED_IS_OVER_FOURTEEN),
                new PolicyAgree(policyHistoryId: 2, policyType: PolicyType.REQUIRED_PERSONAL_INFO_PROCESS),
                new PolicyAgree(policyHistoryId: 3, policyType: PolicyType.REQUIRED_TERMS_OF_SERVICE),
                new PolicyAgree(policyHistoryId: 4, policyType: PolicyType.OPTION_PERSONAL_INFO_MARKETING),
                new PolicyAgree(policyHistoryId: 5, policyType: PolicyType.OPTION_EVENT_MAIL_OR_SMS),
        ]
        policyHistoryRepository.findAllByPolicyTypeInAndIsLatestRevision(_, true) >> { List list, boolean revision ->
            return [
                    new PolicyHistory(policyType: PolicyType.REQUIRED_IS_OVER_FOURTEEN, isLatestRevision: true),
                    new PolicyHistory(policyType: PolicyType.REQUIRED_PERSONAL_INFO_PROCESS, isLatestRevision: true),
                    new PolicyHistory(policyType: PolicyType.REQUIRED_TERMS_OF_SERVICE, isLatestRevision: true),
                    new PolicyHistory(policyType: PolicyType.OPTION_PERSONAL_INFO_MARKETING, isLatestRevision: true),
                    new PolicyHistory(policyType: PolicyType.OPTION_EVENT_MAIL_OR_SMS, isLatestRevision: true),
            ] as List<PolicyHistory>
        }
        when: "1번 유저의 약관을 저장합니다."
        policyAgreeService.saveSignUpPolicy(1L, signUpPolicyTypes)

        then: "history를 찾고 agree를 저장해야합니다."
        true
        1 * policyAgreeRepository.saveAll(_)
    }


    def "선택약관 리스트가 Null이면 IllegalArgumentException 를 반환합니다."() {
        when: "선택약관에 null을 대입합니다."
        policyAgreeService.saveSignUpPolicy(1L, null)

        then: "policyTypes이 null 이면 exception이 발생합니다."
        def exception = thrown(IllegalArgumentException)
        exception.message == "policyTypes이 Null 입니다."
    }

}
