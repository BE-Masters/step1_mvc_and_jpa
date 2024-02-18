package com.example.be_study.service.user.service;

import com.example.be_study.service.user.domain.UserMetric;
import com.example.be_study.service.user.repository.UserMetricRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserMetricService {
    public UserMetricRepository userMetricRepository;
    private static final int AGE_GROUP = 0;
    private static final int USER_COUNT = 1;

    public UserMetricService(UserMetricRepository userMetricRepository) {
        this.userMetricRepository = userMetricRepository;
    }

    @Transactional(readOnly = false)
    public void createVeryManyUser() {

        userMetricRepository.saveAllUserBulkMode();
    }

    @Transactional(readOnly = true)
    public Page<UserMetric> paging(Pageable pageable) {

        Page<UserMetric> userMetricPage = userMetricRepository.findAll(pageable);

        Page<UserMetric> userMetrics = userMetricPage.map(
                userMetric -> new UserMetric(
                        userMetric.getUserId(),
                        userMetric.getAge(),
                        userMetric.getDeviceType()));

        return userMetrics;
    }

    public Map<String, Long> countUsersByAgeGroup() {
        List<Object[]> results = userMetricRepository.countUsersByAgeGroup();

        return results.stream()
                .sorted(Comparator.comparing(result -> getAgeGroupOrder((String) result[AGE_GROUP])))
                .collect(Collectors.toMap(
                        result -> (String) result[AGE_GROUP],
                        result -> (Long) result[USER_COUNT],
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));
    }

    private int getAgeGroupOrder(String ageGroup) {
        switch (ageGroup) {
            case "10대":
                return 1;
            case "20대":
                return 2;
            case "30대":
                return 3;
            case "40대":
                return 4;
            case "50대":
                return 5;
            case "60대":
                return 6;
            case "70대":
                return 7;
            case "80대":
                return 8;
            case "90대 이상":
                return 9;
            default:
                return 10;
        }

    }
}
