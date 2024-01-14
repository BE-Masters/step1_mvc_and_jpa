package com.example.be_study.service.policy.dto;

import com.example.be_study.service.policy.enums.PolicyType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PolicyNeedAgreeResponseDto {

    private boolean isNeedAgreeUser;

    private List<PolicyType> needPolicyType;

    @Builder
    public PolicyNeedAgreeResponseDto(boolean isNeedAgreeUser, List<PolicyType> needPolicyType) {
        this.isNeedAgreeUser = isNeedAgreeUser;
        this.needPolicyType = needPolicyType;
    }

    public static PolicyNeedAgreeResponseDto of(boolean isNeedAgreeUser, List<PolicyType> needPolicyTypeList) {
        return PolicyNeedAgreeResponseDto.builder()
                .isNeedAgreeUser(isNeedAgreeUser)
                .needPolicyType(needPolicyTypeList)
                .build();
    }
}
