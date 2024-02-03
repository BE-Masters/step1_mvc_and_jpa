package com.example.be_study.service.user.repository;

import com.example.be_study.service.user.domain.UserMetric;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMetricRepository extends JpaRepository<UserMetric, Long>, UserMetricDSLRepository {
}
