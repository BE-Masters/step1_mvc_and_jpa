package com.example.be_study.service.policy.service;

import com.example.be_study.common.response.DataResponse;
import com.example.be_study.service.policy.domain.*;
import com.example.be_study.service.policy.dto.PolicyNeedAgreeResponseDto;
import com.example.be_study.service.policy.enums.PolicyAgreeResponseCode;
import com.example.be_study.service.policy.enums.PolicyType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional(readOnly = true)
public class PolicyAgreeService {

    private final PolicyAgreeRepository policyAgreeRepository;
    private final PolicyHistoryRepository policyHistoryRepository;
    private final JPAQueryFactory jpaQueryFactory;

    public PolicyAgreeService(PolicyAgreeRepository policyAgreeRepository, PolicyHistoryRepository policyHistoryRepository, JPAQueryFactory jpaQueryFactory) {
        this.policyAgreeRepository = policyAgreeRepository;
        this.policyHistoryRepository = policyHistoryRepository;
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Transactional(readOnly = false)
    public List<PolicyAgree> saveSignUpPolicy(Long userId, List<PolicyType> policyTypes) {
        if (ObjectUtils.isEmpty(policyTypes)) {
            throw new IllegalArgumentException("policyTypes이 Null 입니다.");
        }

        List<PolicyType> policyAgreeList = Stream.of(PolicyType.SIGN_REQUIRED_LIST, policyTypes)
                .flatMap(List::stream).toList();

        List<PolicyHistory> policyHistories = policyHistoryRepository.findAllByPolicyTypeInAndIsLatestRevision(policyAgreeList, true);

        if (ObjectUtils.isEmpty(policyHistories)) {
            return null;
        }
        List<PolicyAgree> saveList = policyHistories.stream().map(it ->
                PolicyAgree.of(userId, it)
        ).toList();

        return policyAgreeRepository.saveAll(saveList);
    }

    @Transactional(readOnly = true)
    public DataResponse<PolicyNeedAgreeResponseDto> isNeedAgreeUser(Long userId) {
        QPolicyHistory policyHistory = QPolicyHistory.policyHistory;
        List<PolicyHistory> signRequiredList = jpaQueryFactory.selectFrom(policyHistory)
                .where(policyHistory.policyType.in(PolicyType.SIGN_REQUIRED_LIST))
                .where(policyHistory.isLatestRevision.eq(true))
                .fetch();

        List<Long> requiredTypeIds = signRequiredList.stream()
                .map(PolicyHistory::getId)
                .collect(Collectors.toList());

        List<PolicyAgree> policyAgreeList = policyAgreeRepository.findAllByUserIdAndPolicyHistoryIdIn(userId, requiredTypeIds);

        if (ObjectUtils.isEmpty(policyAgreeList)) {
            return new DataResponse<>(PolicyAgreeResponseCode.ALREADY_AGREED,
                    PolicyNeedAgreeResponseDto.of(false, Collections.emptyList()));
        }

        return new DataResponse<>(PolicyAgreeResponseCode.NEED_TO_AGREED,
                PolicyNeedAgreeResponseDto.of(
                        true,
                        policyAgreeList.stream().map(PolicyAgree::getPolicyType).collect(Collectors.toList()))
        );
    }


}
