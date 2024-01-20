package com.example.be_study.controller.policy;

import com.example.be_study.common.response.DataResponse;
import com.example.be_study.service.policy.domain.PolicyHistory;
import com.example.be_study.service.policy.dto.PolicyReviseRequestDto;
import com.example.be_study.service.policy.service.PolicyHistoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/policy/history")
public class PolicyHistoryController {

    private final PolicyHistoryService policyHistoryService;

    public PolicyHistoryController(PolicyHistoryService policyHistoryService) {
        this.policyHistoryService = policyHistoryService;
    }

    @PostMapping("/revise")
    public DataResponse<List<PolicyHistory>> revisePolicyHistory(
            @RequestBody PolicyReviseRequestDto policyReviseRequestDto
    ) {
        return policyHistoryService.revisePolicyHistory(policyReviseRequestDto);
    }
}
