package com.example.be_study.controller.policy;

import com.example.be_study.service.policy.service.PolicyAgreeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/policy/agree")
public class PolicyAgreeController {

    private final PolicyAgreeService policyAgreeService;

    public PolicyAgreeController(PolicyAgreeService policyAgreeService) {
        this.policyAgreeService = policyAgreeService;
    }

    @GetMapping("/isNeedUser")
    public void isNeedAgreeUser(){
        policyAgreeService.isNeedAgreeUser(1L);
    }
}
