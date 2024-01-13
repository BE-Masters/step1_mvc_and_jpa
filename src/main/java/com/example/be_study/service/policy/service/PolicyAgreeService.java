package com.example.be_study.service.policy.service;

import com.example.be_study.service.policy.domain.PolicyAgree;
import com.example.be_study.service.policy.domain.PolicyAgreeRepository;
import com.example.be_study.service.policy.domain.PolicyHistory;
import com.example.be_study.service.policy.domain.PolicyHistoryRepository;
import com.example.be_study.service.policy.enums.PolicyType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Stream;

@Service
@Transactional(readOnly = true)
public class PolicyAgreeService {

    public PolicyAgreeRepository policyAgreeRepository;
    public PolicyHistoryRepository policyHistoryRepository;

    public PolicyAgreeService(PolicyAgreeRepository policyAgreeRepository, PolicyHistoryRepository policyHistoryRepository) {
        this.policyAgreeRepository = policyAgreeRepository;
        this.policyHistoryRepository = policyHistoryRepository;
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


}
