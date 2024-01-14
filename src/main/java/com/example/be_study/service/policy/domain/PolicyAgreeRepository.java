package com.example.be_study.service.policy.domain;

import com.example.be_study.service.policy.enums.PolicyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PolicyAgreeRepository extends JpaRepository<PolicyAgree, Long> {

    List<PolicyAgree> findAllByPolicyTypeIn(List<PolicyType> policyTypes);

    List<PolicyAgree> findAllByUserIdAndPolicyHistoryIdIn(Long userId, List<Long> policyHistoryIds);
}
