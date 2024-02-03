package com.example.be_study.service.user.repository;

import com.example.be_study.service.user.domain.QUserMetric;
import com.example.be_study.service.user.domain.UserMetric;
import com.example.be_study.service.user.enums.DeviceType;
import com.example.be_study.service.user.enums.ProviderType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public class UserMetricDSLRepositoryImpl implements UserMetricDSLRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private DeviceType getRandomDeviceType(Random random) {
        int randomIndex = random.nextInt(3);
        return switch (randomIndex) {
            case 0 -> DeviceType.AOS;
            case 1 -> DeviceType.IOS;
            case 2 -> DeviceType.WEB;
            default -> throw new IllegalArgumentException("잘못된 랜덤 숫자가 생겼습니다. getRandomDeviceTYpe");
        };
    }

    private ProviderType getRandomProviderType(Random random) {
        int randomIndex = random.nextInt(4);
        return switch (randomIndex) {
            case 0 -> ProviderType.KAKAO;
            case 1 -> ProviderType.NAVER;
            case 2 -> ProviderType.FACEBOOK;
            case 3 -> ProviderType.ORIGIN;
            default -> throw new IllegalArgumentException("잘못된 랜덤 숫자가 생겼습니다.");
        };
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<UserMetric> findByTopByOrderByUserIdDesc() {
        QUserMetric userMetric = QUserMetric.userMetric;

        return Optional.ofNullable(jpaQueryFactory.selectFrom(userMetric)
                .orderBy(userMetric.userId.desc())
                .fetchFirst());
    }

    @Override
    @Transactional(readOnly = false)
    public void saveAllUserBulkMode() {
        Random random = new Random();
        Optional<UserMetric> userMetric = findByTopByOrderByUserIdDesc();
        List<UserMetric> userMetricList = new ArrayList<>();

        int startIndex = userMetric.map(metric -> metric.getUserId().intValue() + 1).orElse(1);
        int batchSize = 2000000;

        for (int i = startIndex; i <= startIndex + batchSize; i++) {
            userMetricList.add(UserMetric.builder()
                    .userId((long) i)
                    .age((short) (random.nextInt(100) + 1))
                    .deviceType(getRandomDeviceType(random))
                    .build());

            System.out.println(i);
        }

        String sql = " INSERT INTO user_metric (" +
                "user_id, age, device_type" +
                ") values (" +
                "?, ?, ?"
                + ")";

        jdbcTemplate.batchUpdate(
                sql,
                userMetricList,
                batchSize, ((ps, argument) -> {
                    ps.setLong(1, argument.getUserId());
                    ps.setShort(2, argument.getAge());
                    ps.setString(3, argument.getDeviceType().toString());
                })
        );
    }
}
