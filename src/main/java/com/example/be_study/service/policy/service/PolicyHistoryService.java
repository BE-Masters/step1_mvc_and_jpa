package com.example.be_study.service.policy.service;

import com.example.be_study.common.response.DataResponse;
import com.example.be_study.service.policy.domain.PolicyHistory;
import com.example.be_study.service.policy.domain.PolicyHistoryRepository;
import com.example.be_study.service.policy.dto.PolicyReviseRequestDto;
import com.example.be_study.service.policy.enums.PolicyHistoryResponseCode;
import com.example.be_study.service.policy.enums.PolicyType;
import com.example.be_study.service.policy.exception.PolicyHistoryException;
import com.example.be_study.service.s3.enums.HouseS3Bucket;
import com.example.be_study.service.s3.service.S3Service;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@Service
public class PolicyHistoryService {
    private final PolicyHistoryRepository policyHistoryRepository;
    private final S3Service s3Service;

    public PolicyHistoryService(PolicyHistoryRepository policyHistoryRepository, S3Service s3Service) {
        this.policyHistoryRepository = policyHistoryRepository;
        this.s3Service = s3Service;
    }

    @Transactional(readOnly = false)
    public DataResponse<PolicyHistory> revisePolicyHistory(
            @ModelAttribute PolicyReviseRequestDto policyReviseRequestDto
    ) {
        String pdfFilePath = s3Service.uploadFile(
                policyReviseRequestDto.getPdfFile(),
                HouseS3Bucket.PolicyPdfBucket
        );

        //새롭게 등록할 개체를 생성
        PolicyHistory policyHistory = policyReviseRequestDto.ofRevisePolicyHistory(pdfFilePath);

        //동일한 약관 타입과 최근 개정판을 가져옵니다.
        PolicyType registerType = policyHistory.getPolicyType();

        //기존 약관이 존재하면 최신 여부를 false로 바꾼다.
        Optional<PolicyHistory> opPrevHistory = policyHistoryRepository.findByPolicyTypeAndIsLatestRevision(registerType, true);
        if (opPrevHistory.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 약관을 개정하려고 했습니다. 개발자와 상의해서 추가해주세요.");
        }

        opPrevHistory.get().setLatestRevision(false);
        return new DataResponse<>(PolicyHistoryResponseCode.SUCCESS, policyHistoryRepository.save(policyHistory));
    }

    @Transactional(readOnly = true)
    public byte[] downloadPolicyPdfFile(
            Long userId,
            Long policyHistoryId
    ) {
        Optional<PolicyHistory> policyHistoryOp = policyHistoryRepository.findById(policyHistoryId);
        PolicyHistory policyHistory = policyHistoryOp.orElseThrow(() ->
                new PolicyHistoryException(
                        HttpStatusCode.valueOf(400),
                        "존재하지 않는 이용약관 ID입니다. 요청한 userId : " + userId
                )
        );

        byte[] pdfFile = s3Service.downloadFile(HouseS3Bucket.PolicyPdfBucket, policyHistory.getPdfFilePath());
        return pdfFile;
    }
}
