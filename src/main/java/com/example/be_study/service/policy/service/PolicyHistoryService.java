package com.example.be_study.service.policy.service;

import com.example.be_study.service.policy.domain.PolicyHistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class PolicyHistoryService {
    private PolicyHistoryRepository policyHistoryRepository;

    public PolicyHistoryService(PolicyHistoryRepository policyHistoryRepository) {
        this.policyHistoryRepository = policyHistoryRepository;
    }

}
