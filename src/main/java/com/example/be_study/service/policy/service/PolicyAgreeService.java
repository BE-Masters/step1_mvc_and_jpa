package com.example.be_study.service.policy.service;

import com.example.be_study.service.policy.domain.PolicyAgree;
import com.example.be_study.service.policy.domain.PolicyAgreeRepository;
import com.example.be_study.service.policy.domain.PolicyHistory;
import lombok.Builder;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PolicyAgreeService {

    public PolicyAgreeRepository policyAgreeRepository;

    public PolicyAgreeService(PolicyAgreeRepository policyAgreeRepository) {
        this.policyAgreeRepository = policyAgreeRepository;
    }

    @Transactional(readOnly = false)
    public PolicyAgree savePolicyAgree() {
        return policyAgreeRepository.save(PolicyAgree.of());
    }

}
