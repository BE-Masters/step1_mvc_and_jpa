package com.example.be_study.service.user.repository;

import com.example.be_study.service.user.domain.UserMetric;

import java.util.List;
import java.util.Optional;

public interface UserMetricDSLRepository {

    void saveAllUserBulkMode();

    Optional<UserMetric> findByTopByOrderByUserIdDesc();

    List<Short> getUserAgeStatistics();
}
