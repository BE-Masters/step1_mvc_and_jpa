package com.example.be_study.policy.agree


import com.example.be_study.service.policy.domain.PolicyAgreeRepository
import com.example.be_study.service.policy.domain.PolicyHistoryRepository
import com.example.be_study.service.policy.domain.QPolicyHistory
import com.example.be_study.service.policy.service.PolicyAgreeService
import com.querydsl.jpa.impl.JPAQueryFactory
import spock.lang.Specification
import spock.lang.Subject

abstract class PolicyAgreeServiceTest extends Specification {

    @Subject
    PolicyAgreeService policyAgreeService

    PolicyAgreeRepository policyAgreeRepository = Mock()
    PolicyHistoryRepository policyHistoryRepository = Mock()
    JPAQueryFactory jpaQueryFactory = Mock()
    QPolicyHistory policyHistory = QPolicyHistory.policyHistory

    def setup() {
        policyAgreeService = new PolicyAgreeService(
                policyAgreeRepository,
                policyHistoryRepository,
                jpaQueryFactory
        )
    }
}
