package com.example.be_study.controller.admin;

import com.example.be_study.common.response.DataResponse;
import com.example.be_study.service.user.domain.UserMetric;
import com.example.be_study.service.user.service.UserMetricService;
import com.example.be_study.utils.AdminCheck;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/api/v1/admin")
@RestController
public class UserMetricController {

    private final UserMetricService userMetricService;

    public UserMetricController(UserMetricService userMetricService) {
        this.userMetricService = userMetricService;
    }


    @AdminCheck
    @GetMapping("/metricPage")
    public Page<UserMetric> getUserMetricPage(@PageableDefault(page = 1, size = 10)Pageable pageable, Authentication authentication){
        return userMetricService.getUserMetricPage(pageable);
    }

    @AdminCheck
    @GetMapping("/userAgeGroup")
    public ResponseEntity<Map<String, Long>> getUserAgeGroup(Authentication authentication){
        Map<String, Long> userAgeGroup = userMetricService.getUserAgeGroup();
        return ResponseEntity.ok(userAgeGroup);
    }
}
