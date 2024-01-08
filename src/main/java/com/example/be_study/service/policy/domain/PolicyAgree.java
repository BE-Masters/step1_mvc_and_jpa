package com.example.be_study.service.policy.domain;

import com.example.be_study.service.base.AbstractBaseEntity;
import com.example.be_study.service.policy.enums.PolicyType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table
@AttributeOverride(name = "id", column = @Column(name = "policy_agree_id"))
@Entity(name = "policy_agree")
public class PolicyAgree extends AbstractBaseEntity {

    @Enumerated
    @Column(name = "policy_type", nullable = false)
    private PolicyType policyType;

    @Column(name = "policy_history_id", nullable = false)
    private Long policyHistoryId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Builder
    public PolicyAgree(PolicyType policyType, Long policyHistoryId, Long userId) {
        this.policyType = policyType;
        this.policyHistoryId = policyHistoryId;
        this.userId = userId;
    }

    public static PolicyAgree of() {
        return PolicyAgree.builder()
                /*.policyType()
                .policyHistoryId()
                .userId()*/
                .build();
    }
}
