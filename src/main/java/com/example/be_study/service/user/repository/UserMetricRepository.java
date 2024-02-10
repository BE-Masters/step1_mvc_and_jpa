package com.example.be_study.service.user.repository;

import com.example.be_study.service.user.domain.UserMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface UserMetricRepository extends JpaRepository<UserMetric, Long>, UserMetricDSLRepository {
    @Query("SELECT CASE " +
            "WHEN um.age < 20 THEN '10대' " +
            "WHEN um.age BETWEEN 20 AND 29 THEN '20대' " +
            "WHEN um.age BETWEEN 30 AND 39 THEN '30대' " +
            "WHEN um.age BETWEEN 40 AND 49 THEN '40대' " +
            "WHEN um.age BETWEEN 50 AND 59 THEN '50대' " +
            "WHEN um.age BETWEEN 60 AND 69 THEN '60대' " +
            "WHEN um.age BETWEEN 70 AND 79 THEN '70대' " +
            "WHEN um.age BETWEEN 80 AND 89 THEN '80대' " +
            "WHEN um.age >= 90 THEN '90대 이상' " +
            "END AS ageGroup, COUNT(um) AS totalCnt " +
            "FROM UserMetric um " +
            "GROUP BY ageGroup " +
            "ORDER BY MIN(um.age)")
    List<Object[]> countUsersByAgeGroup();


}
