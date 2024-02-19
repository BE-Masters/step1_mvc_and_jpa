package com.example.be_study.service.admin.repository;

import com.example.be_study.service.admin.dto.AdminUserInfoDto;
import com.example.be_study.service.admin.dto.AdminUserListRequest;
import com.example.be_study.service.admin.dto.PagingRequest;
import com.example.be_study.service.admin.dto.UserAgeCount;
import com.example.be_study.service.user.domain.QUserMetric;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AdminUserRepositoryImpl implements AdminUserCustomRepository {

    private final JPAQueryFactory queryFactory;

    private final QUserMetric userMetric = QUserMetric.userMetric;

    public AdminUserRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    /**
     *  사용자 목록 조회
     */
    @Override
    public Page<AdminUserInfoDto> findAll(AdminUserListRequest request) {
        Pageable paging = new PagingRequest(request.getPage(), request.getSize()).of();

        List<AdminUserInfoDto> result = queryFactory
                .select(Projections.constructor(AdminUserInfoDto.class,
                        userMetric.userId, userMetric.age))
                .from(userMetric)
                .offset(paging.getOffset())
                .limit(paging.getPageSize())
                .fetch();

        Long total = Optional.ofNullable(queryFactory
                .select(userMetric.countDistinct())
                .from(userMetric)
                .fetchOne()).orElse(0L);

        return new PageImpl<>(result, paging, total);
    }

    /**
     * 사용자 연령대별 카운트
     *
     * 개선전 속도 : 41.45sec
     * 개선후 속도 : 11.88sec
     *
     * 개선전 문제점
     * 1. 인덱스를 활용하지 않음
     * 2. 여러 개의 Case 문을 사용하여 각 연령대별로 count 하는 방식 -> 많은 메모리 사용
     *
     * 개선 방안
     * 1. groupBy 를 통해 인덱스 활용 및 집계 함수 사용
     * 2. stream & collect 사용 -> 쿼리 결과를 한 번에 가져온 후, 연산 수행
     * 3. 동일한 연령대에 대한 count 는 1번만 이루어짐
     */
    @Override
    public UserAgeCount userAgeCount() {
        Map<String, Long> ageCount = queryFactory
                .select(userMetric.age, userMetric.count())
                .from(userMetric)
                .groupBy(userMetric.age)
                .fetch()
                .stream() // 조회한 결과를 stream 으로 변환
                .collect(Collectors.toMap( // toMap : stream 요소를 Key & Value 형태로 매핑하여 Map 생성
                        age -> getAgeRange(age.get(userMetric.age)), // 나이를 가져와 연령대를 Key 로 매핑
                        age -> age.get(userMetric.count()), // 해당 나이의 사용자 수 count
                        Long::sum // 연령대별 최종 count 값 추출 (동일한 연령대의 Key 가 존재하면 해당 Key 의 count 값 더하기)
                ));

        return new UserAgeCount( // ageCount Map 을 통해 UserAgeCount 객체 생성, 데이터가 없는 연령대는 0 반환
                ageCount.getOrDefault("20-29", 0L).intValue(), // getOrDefault : 해당 Key 에 Value 가 없으면 defaultValue 반환
                ageCount.getOrDefault("30-39", 0L).intValue(),
                ageCount.getOrDefault("40-49", 0L).intValue(),
                ageCount.getOrDefault("50-59", 0L).intValue(),
                ageCount.getOrDefault("60-69", 0L).intValue(),
                ageCount.getOrDefault("70-79", 0L).intValue(),
                ageCount.getOrDefault("80-89", 0L).intValue(),
                ageCount.getOrDefault("90-99", 0L).intValue()
        );
    }

    private String getAgeRange(short userAge) {
        int startAge = (userAge / 10) * 10; // 시작 나이 추출
        int endAge = startAge + 9; // 시작 나이에 9를 더해서 끝 나이 추출
        return String.format("%d-%d", startAge, endAge); // 해당 나이의 연령대 추출
    }
}
