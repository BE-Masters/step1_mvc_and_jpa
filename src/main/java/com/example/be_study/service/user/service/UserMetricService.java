package com.example.be_study.service.user.service;

import com.example.be_study.service.user.domain.UserMetric;
import com.example.be_study.service.user.domain.UserPrincipal;
import com.example.be_study.service.user.repository.UserMetricRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserMetricService {
    private final UserMetricRepository userMetricRepository;

    public UserMetricService(UserMetricRepository userMetricRepository) {
        this.userMetricRepository = userMetricRepository;
    }

    @Transactional(readOnly = false)
    public void createVeryManyUser() {

        userMetricRepository.saveAllUserBulkMode();
    }

    public String getUserStatisticsList() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<Short> ages = userMetricRepository.getUserAgeStatistics();
        stopWatch.stop();
        log.info(" DB 쿼리 조회 속도 {}", stopWatch.getTotalTimeMillis());

        StopWatch stopWatch2 = new StopWatch();
        stopWatch2.start();
        Map<Integer, List<Short>> ageMapping = ages.parallelStream().collect(Collectors.groupingBy(it -> {
            int div = (it / 10);
            return div * 10;
        }));

        stopWatch2.stop();
        StringBuffer sb = new StringBuffer();
        List<Integer> sortedList = ageMapping.keySet().stream().sorted().toList();
        sortedList.forEach(it -> sb.append(it + "대 : " + ageMapping.get(it).size() + "명\n"));
        log.info(" 나이대별 통계 자료 생성 속도 {}", stopWatch2.getTotalTimeMillis());
        return sb.toString();
    }

    public Page<UserMetric> pagingUserMetric(Pageable pageable, UserPrincipal userPrincipal) {
        return userMetricRepository.findAll(pageable);
    }
}
