package com.example.be_study.service.user.repository;

import com.example.be_study.service.user.domain.UserMetric;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserMetricRepository extends JpaRepository<UserMetric, Long>, UserMetricDSLRepository {

    List<UserMetric> findTop100ByOrderByUserIdAsc(); // userId를 기준으로 오름차순 정렬했을 때, 상위 100개 추출
}
