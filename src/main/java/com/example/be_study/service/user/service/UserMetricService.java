package com.example.be_study.service.user.service;

import com.example.be_study.service.user.domain.QUserMetric;
import com.example.be_study.service.user.domain.UserMetric;
import com.example.be_study.service.user.repository.UserMetricRepository;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserMetricService {
    public UserMetricRepository userMetricRepository;

    private final EntityManager entityManager;

    public UserMetricService(UserMetricRepository userMetricRepository, EntityManager entityManager) {
        this.userMetricRepository = userMetricRepository;
        this.entityManager = entityManager;
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

    @Cacheable("userAgeCache")
    public Map<String, Long> countUsersByAgeGroup() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QUserMetric um = QUserMetric.userMetric;

        // 나이대별 사용자 수 group by 하기
        List<Tuple> results = queryFactory
                .select(um.age, um.userId.count())
                .from(um)
                .groupBy(um.age)
                .fetch();

         // 나이대별로 순서대로 나오게 해줘요..
        List<String> orderedKeys = List.of("어린이","10대", "20대", "30대", "40대", "50대", "60대", "70대", "80대", "90대 이상", "기타");

        Map<String, Long> sortedAgeGroupCounts = results.stream()
                .collect(Collectors.groupingBy(
                        row -> shortToAgeGroup(row.get(0, Short.class)), // 20-> 20대 으로 수정
                        LinkedHashMap::new, // LinkedHashMap을 사용하면 순서가 유지된다고 ...
                        Collectors.summingLong(row -> row.get(1, Long.class)) // 각 그룹의 카운트 합산
                ));

        return sortedAgeGroupCounts;
    }

    private String shortToAgeGroup(Short age) {
        if (age >= 0 && age <= 9) return "어린이";
        else if (age >= 10 && age <= 19) return "10대";
        else if (age >= 20 && age <= 29) return "20대";
        else if (age >= 30 && age <= 39) return "30대";
        else if (age >= 40 && age <= 49) return "40대";
        else if (age >= 50 && age <= 59) return "50대";
        else if (age >= 60 && age <= 69) return "60대";
        else if (age >= 70 && age <= 79) return "70대";
        else if (age >= 80 && age <= 89) return "80대";
        else if (age >= 90) return "90대 이상";
        return "기타";
    }
    // Map<userId, List<Userid를 가진 유저정보>>
    public Map<Long, List<UserMetric>> userInfGrouping() {
        List<UserMetric> userMetricsList = userMetricRepository.findAll();

        return userMetricsList.stream()
                .limit(100)
                .collect(Collectors.groupingBy(UserMetric::getUserId));
    }

    // 키-값이랑 쌍으로 이루어져있음
    public Map<Long, UserMetric> userInfoToMap() {
        List<UserMetric> userMetricsList = userMetricRepository.findAll();

        return userMetricsList.stream()
                .limit(100)
                .collect(Collectors.toMap(UserMetric::getUserId, userMetric-> userMetric));
    }

}
