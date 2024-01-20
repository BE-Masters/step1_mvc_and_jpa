package com.example.be_study.controller.policy;

import com.example.be_study.service.policy.service.PolicyAgreeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/apl/v1/policy/agree")
public class PolicyAgreeController {

    private final PolicyAgreeService policyAgreeService;

    public PolicyAgreeController(PolicyAgreeService policyAgreeService) {
        this.policyAgreeService = policyAgreeService;
    }

    public void isNeedAgreeUser(){
        policyAgreeService.isNeedAgreeUser(1L);
    }
}
