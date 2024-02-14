package com.example.be_study.service.user.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.*;

public class UserMetricRepositoryImpl {

    @PersistenceContext
    private EntityManager em;

    public Map<String, Long> getUserAgeGroup() {
        LinkedHashMap<String, Long> userAgeGroup = new LinkedHashMap<>();
        List<Object[]> result = new ArrayList<>();

        try{
            StringBuilder jpql = getStringBuilder();

            Query nativeQuery = em.createNativeQuery(jpql.toString());
            result = nativeQuery.getResultList();

            for (Object[] row: result){
                userAgeGroup.put((String) row[0], (Long) row[1]);
            }

        }catch (Exception e){
            e.getMessage();
        }
        return userAgeGroup;
    }

    private static StringBuilder getStringBuilder() {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT CASE \n" +
                "WHEN age < 20 THEN '10대' \n" +
                "WHEN age BETWEEN 20 AND 29 THEN '20대' \n" +
                "WHEN age BETWEEN 30 AND 39 THEN '30대' \n" +
                "WHEN age BETWEEN 40 AND 49 THEN '40대' \n" +
                "WHEN age BETWEEN 50 AND 59 THEN '50대' \n" +
                "WHEN age BETWEEN 60 AND 69 THEN '60대' \n" +
                "WHEN age BETWEEN 70 AND 79 THEN '70대' \n" +
                "WHEN age BETWEEN 80 AND 89 THEN '80대' \n" +
                "WHEN age >= 90 THEN '90대 이상' \n" +
                "END AS ageGroup, COUNT(*) AS totalCnt \n" +
                "FROM house.user_metric  \n" +
                "GROUP BY ageGroup \n" +
                "ORDER BY MIN(age)");
        return jpql;
    }
}
