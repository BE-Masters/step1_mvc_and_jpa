package com.example.be_study.service.policy.service;

import com.example.be_study.common.response.DataResponse;
import com.example.be_study.service.policy.domain.PolicyHistory;
import com.example.be_study.service.policy.domain.PolicyHistoryRepository;
import com.example.be_study.service.policy.dto.PolicyReviseRequestDto;
import com.example.be_study.service.policy.enums.PolicyHistoryResponseCode;
import com.example.be_study.service.policy.enums.PolicyType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class PolicyHistoryService {
    private final PolicyHistoryRepository policyHistoryRepository;

    public PolicyHistoryService(PolicyHistoryRepository policyHistoryRepository) {
        this.policyHistoryRepository = policyHistoryRepository;
    }


    @Transactional(readOnly = false)
    public DataResponse<PolicyHistory> revisePolicyHistory(
            @ModelAttribute PolicyReviseRequestDto policyReviseRequestDto
    ) {
        //AWS S3 구현
        String pdfFilePath = "";

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
}
