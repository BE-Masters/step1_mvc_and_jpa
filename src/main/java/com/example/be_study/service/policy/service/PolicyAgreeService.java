package com.example.be_study.service.policy.service;

import com.example.be_study.service.policy.domain.PolicyAgree;
import com.example.be_study.service.policy.domain.PolicyAgreeRepository;
import com.example.be_study.service.policy.enums.PolicyType;
import com.example.be_study.service.policy.enums.SignUpPolicyType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PolicyAgreeService {

    public PolicyAgreeRepository policyAgreeRepository;

    public PolicyAgreeService(PolicyAgreeRepository policyAgreeRepository) {
        this.policyAgreeRepository = policyAgreeRepository;
    }

    @Transactional(readOnly = false)
    public List<PolicyAgree> saveSignUpPolicy(Long userId, List<SignUpPolicyType> policyTypes) {
        return null;
    }


}
