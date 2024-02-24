package com.example.be_study.service.user.repository;

import com.example.be_study.service.user.domain.UserMetric;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

public class UserMetricRepositoryImpl {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public List getUserAgeGroup() {
        List result = new ArrayList<>();
        StringBuilder jpql = getStringBuilder(); //조회할 쿼리 생성
        Query nativeQuery = em.createNativeQuery(jpql.toString()); //쿼리 실행
        result = nativeQuery.getResultList(); //결과를 컬렉션으로 반환
        return result;
    }
    private static StringBuilder getStringBuilder() {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT age from house.user_metric where age between 20 and 99");//쿼리에서 계산식에서 변경
        return jpql;
    }
}
