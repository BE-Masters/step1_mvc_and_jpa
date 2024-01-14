package com.example.be_study.service.policy.domain;

import com.example.be_study.service.policy.enums.PolicyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PolicyHistoryRepository extends CrudRepository<PolicyHistory, Long> {

    List<PolicyHistory> findAllByPolicyTypeInAndIsLatestRevision(List<PolicyType> policyTypes, boolean isLatestRevision);
}
