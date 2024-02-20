package com.example.be_study.service.user.service;

import com.example.be_study.common.response.DataResponse;
import com.example.be_study.service.user.domain.UserMetric;
import com.example.be_study.service.user.enums.DeviceType;
import com.example.be_study.service.user.enums.UserMetricResponseMessage;
import com.example.be_study.service.user.repository.UserMetricRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserMetricService {
    public UserMetricRepository userMetricRepository;

    public UserMetricService(UserMetricRepository userMetricRepository) {
        this.userMetricRepository = userMetricRepository;
    }

    @Transactional(readOnly = false)
    public void createVeryManyUser() {

        userMetricRepository.saveAllUserBulkMode();
    }

    /**
     * 사용자 리스트 1
     */
    @Transactional(readOnly = true)
    public DataResponse<Map<DeviceType, List<UserMetric>>> userMetricListA() {
        List<UserMetric> userMetricList = userMetricRepository.findTop100ByOrderByUserIdAsc(); // userMetric 정보 100개 추출

        // 미션 5-1. groupingBy(그룹화) 사용
        // DeviceType 을 기준으로 그룹화 -> 디바이스 종류별로 user 를 그룹화하여, 디바이스 종류에 따라 처리해야 하는 로직에 사용하면 유용할 것 같다.
        // Long 이 아닌 DeviceType 을 사용한 이유 : DeviceType 으로 그룹화했을 때 groupingBy 의 목적이 더 잘 나타나는 것 같아서 수정했습니다...!
        Map<DeviceType, List<UserMetric>> userList = userMetricList.stream()
                .collect(Collectors.groupingBy(UserMetric::getDeviceType));

        return new DataResponse<>(UserMetricResponseMessage.SUCCESS, userList);
    }

    /**
     * 사용자 리스트 2
     */
    @Transactional(readOnly = true)
    public DataResponse<Map<Long, UserMetric>> userMetricListB() {
        List<UserMetric> userMetricList = userMetricRepository.findTop100ByOrderByUserIdAsc(); // userMetric 정보 100개 추출

        // 미션 5-2. toMap 사용 : Map 으로 변환
        // toMap 은 stream 의 처리 결과를 Map 으로 변환할 때 사용된다.
        Map<Long, UserMetric> userList = userMetricList.stream()
                .collect(Collectors.toMap(UserMetric::getUserId, // Key : userId
                        Function.identity(), // Value : UserMetric, UserMetric 객체 자체가 Value 가 된다.
                        (a, b) -> b)); // Key 가 중복될 경우, 새로운 값으로 덮어쓴다. 중복에 대한 대응을 하지 않으면 Duplicate key 에러가 발생된다.

        return new DataResponse<>(UserMetricResponseMessage.SUCCESS, userList);
    }
}
