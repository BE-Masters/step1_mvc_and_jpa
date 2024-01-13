package com.example.be_study.service.policy.service;

import com.example.be_study.service.policy.domain.PolicyAgree;
import com.example.be_study.service.policy.domain.PolicyAgreeRepository;
import com.example.be_study.service.policy.domain.PolicyHistory;
import com.example.be_study.service.policy.enums.PolicyType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Stream;

@Service
@Transactional(readOnly = true)
public class PolicyAnalyticsService {

    public PolicyAgreeRepository policyAgreeRepository;

    public PolicyAnalyticsService(PolicyAgreeRepository policyAgreeRepository) {
        this.policyAgreeRepository = policyAgreeRepository;
    }

    @Transactional(readOnly = false)
    public List<PolicyAgree> getOptionAgreeUserInfo() {
        return null;
    }


}
