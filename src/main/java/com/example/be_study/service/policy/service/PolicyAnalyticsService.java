package com.example.be_study.service.policy.service;

import com.example.be_study.service.policy.domain.PolicyAgree;
import com.example.be_study.service.policy.domain.PolicyAgreeRepository;
import com.example.be_study.service.policy.enums.PolicyType;
import com.example.be_study.service.user.domain.User;
import com.example.be_study.service.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PolicyAnalyticsService {

    private final PolicyAgreeRepository policyAgreeRepository;
    private final UserService userService;

    public PolicyAnalyticsService(PolicyAgreeRepository policyAgreeRepository, UserService userService) {
        this.policyAgreeRepository = policyAgreeRepository;
        this.userService = userService;
    }

    /**
     * TODO 통계 데이터 이니 캐싱 적용하기
     */
    @Transactional(readOnly = true)
    public List<User> getOptionAgreeUserInfo() {
        List<PolicyAgree> policyAgrees = policyAgreeRepository.findAllByPolicyTypeIn(PolicyType.SIGN_OPTION_LIST);

        if(ObjectUtils.isEmpty(policyAgrees)){
            return Collections.emptyList();
        }

        List<Long> userIds = policyAgrees.stream()
                .map(PolicyAgree::getUserId)
                .collect(Collectors.toList());
        return userService.findAllByUserIdIn(userIds);
    }
}
