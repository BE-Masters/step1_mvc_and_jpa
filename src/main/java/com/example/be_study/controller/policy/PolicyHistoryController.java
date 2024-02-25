package com.example.be_study.controller.policy;

import com.example.be_study.common.response.DataResponse;
import com.example.be_study.service.policy.domain.PolicyHistory;
import com.example.be_study.service.policy.dto.PolicyReviseRequestDto;
import com.example.be_study.service.policy.service.PolicyHistoryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/policy/history")
public class PolicyHistoryController {

    private final PolicyHistoryService policyHistoryService;

    public PolicyHistoryController(PolicyHistoryService policyHistoryService) {
        this.policyHistoryService = policyHistoryService;
    }

    @PostMapping(value = "/revise", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public DataResponse<PolicyHistory> revisePolicyHistory(
            @ModelAttribute PolicyReviseRequestDto policyReviseRequestDto
    ) {
        return policyHistoryService.revisePolicyHistory(policyReviseRequestDto);
    }

    @GetMapping(value = "/pdf-file/{policyHistoryId}")
    public ResponseEntity<byte[]> getPolicyHistoryPdfFile(
            @PathVariable("policyHistoryId") Long policyHistoryId
    ) {
        return ResponseEntity.ok(policyHistoryService.downloadPolicyPdfFile(1L, policyHistoryId));
    }
}
