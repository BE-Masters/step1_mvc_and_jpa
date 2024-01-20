package com.example.be_study.service.policy.domain;

import com.example.be_study.service.base.AbstractBaseUserByEntity;
import com.example.be_study.service.policy.enums.PolicyType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table
@AttributeOverride(name = "id", column = @Column(name = "policy_history_id"))
@Entity(name = "policy_history")
public class PolicyHistory extends AbstractBaseUserByEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "policy_type", nullable = false)
    private PolicyType policyType;

    @Column(name = "revision_date", nullable = false)
    private LocalDateTime revisionDate;

    @Column(name = "pdf_file_path", nullable = false)
    private String pdfFilePath;

    @Column(name = "required", nullable = false)
    private boolean required;

    @Column(name = "is_latest_revision", nullable = false)
    private boolean isLatestRevision;

    @Builder
    public PolicyHistory(PolicyType policyType, LocalDateTime revisionDate, String pdfFilePath, boolean required, boolean isLatestRevision) {
        this.policyType = policyType;
        this.revisionDate = revisionDate;
        this.pdfFilePath = pdfFilePath;
        this.required = required;
        this.isLatestRevision = isLatestRevision;
    }
}
