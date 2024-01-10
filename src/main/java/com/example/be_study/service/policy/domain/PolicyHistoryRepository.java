package com.example.be_study.service.policy.domain;

import com.example.be_study.service.policy.enums.PolicyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PolicyHistoryRepository extends JpaRepository<PolicyHistory, Long> {

    List<PolicyHistory> findAllByPolicyTypeInAndIsLatestRevision(List<PolicyType> policyTypes, boolean isLatestRevision);
}
