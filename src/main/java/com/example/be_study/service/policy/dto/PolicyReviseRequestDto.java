package com.example.be_study.service.policy.dto;

import com.example.be_study.service.policy.domain.PolicyHistory;
import com.example.be_study.service.policy.enums.PolicyType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
public class PolicyReviseRequestDto {

    private PolicyType policyType;

    private MultipartFile pdfFile;

    public PolicyHistory ofRevisePolicyHistory(String pdfFilePath) {
        return PolicyHistory.builder()
                .policyType(policyType)
                .required(PolicyType.isRequiredType(policyType))
                .pdfFilePath(pdfFilePath)
                .revisionDate(LocalDateTime.now())
                .isLatestRevision(true)
                .build();
    }
}
