package com.example.be_study.service.policy.service;

import com.example.be_study.common.response.DataResponse;
import com.example.be_study.common.response.DataResponseCode;
import com.example.be_study.service.policy.domain.PolicyHistory;
import com.example.be_study.service.policy.domain.PolicyHistoryRepository;
import com.example.be_study.service.policy.dto.PolicyReviseRequestDto;
import com.example.be_study.service.policy.enums.PolicyAgreeResponseCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PolicyHistoryService {
    private final PolicyHistoryRepository policyHistoryRepository;

    public PolicyHistoryService(PolicyHistoryRepository policyHistoryRepository) {
        this.policyHistoryRepository = policyHistoryRepository;
    }


    @Transactional(readOnly = false)
    public DataResponse<List<PolicyHistory>> revisePolicyHistory(
            PolicyReviseRequestDto policyReviseRequestDto
    ) {
        PolicyHistory policyHistory = policyReviseRequestDto.ofRevisePolicyHistory("");
        

        //return new DataResponse(PolicyAgreeResponseCode)
        return null;
    }
}
