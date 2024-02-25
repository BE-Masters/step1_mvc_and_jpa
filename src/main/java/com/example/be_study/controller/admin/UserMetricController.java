package com.example.be_study.controller.admin;

import com.example.be_study.common.response.DataResponse;
import com.example.be_study.service.user.domain.User;
import com.example.be_study.service.user.domain.UserMetric;
import com.example.be_study.service.user.enums.DeviceType;
import com.example.be_study.service.user.enums.UserResponseMessage;
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

import java.util.LinkedHashMap;
import java.util.List;
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

    /*
    * 사용자 나이 통계 api 개선
    *개선사항: Query에서 계산하는 방식이 아닌 조건에 부합한 나이 데이터만 조회해 서버에서 통계 계산    *
    * */
//    @AdminCheck
    @GetMapping("/userAgeGroup") // mission 5
    public DataResponse<LinkedHashMap<String, Integer>> getUserAgeGroup(Authentication authentication){
        LinkedHashMap<String, Integer> userAgeGroup = userMetricService.getUserAgeGroup();
        return new DataResponse<>(UserResponseMessage.SUCCESS, userAgeGroup);
    }

    @GetMapping("/matricMapGroup") // mission 6 grouping
    public DataResponse<Map<Short, List<UserMetric>>> matricMapGroup(Authentication authentication){
        Map<Short, List<UserMetric>> userMapGroup = userMetricService.matricMapGroup();
        return new DataResponse<>(UserResponseMessage.SUCCESS, userMapGroup);
    }

    @GetMapping("/matricToMap") // mission 6 toMap
    public DataResponse<Map<Long, UserMetric>> matricToMap(Authentication authentication){
        Map<Long, UserMetric> userToMap = userMetricService.matricToMap();
        return new DataResponse<>(UserResponseMessage.SUCCESS, userToMap);
    }

}
