package com.example.be_study.controller.user;

import com.example.be_study.common.response.DataResponse;
import com.example.be_study.service.user.domain.UserMetric;
import com.example.be_study.service.user.enums.DeviceType;
import com.example.be_study.service.user.service.UserMetricService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userMetric")
public class UserMetricController {

    private final UserMetricService userMetricService;

    public UserMetricController(UserMetricService userMetricService) {
        this.userMetricService = userMetricService;
    }

    /**
     * 사용자 리스트 1
     */
    @GetMapping(value = "/list-A", name = "사용자 리스트 1")
    public DataResponse<Map<DeviceType, List<UserMetric>>> userMetricListA() {
        DataResponse<Map<DeviceType, List<UserMetric>>> response = userMetricService.userMetricListA();
        return response;
    }

    /**
     * 사용자 리스트 2
     */
    @GetMapping(value = "/list-B", name = "사용자 리스트 2")
    public DataResponse<Map<Long, UserMetric>> userMetricListB() {
        DataResponse<Map<Long, UserMetric>> response = userMetricService.userMetricListB();
        return response;
    }
}
