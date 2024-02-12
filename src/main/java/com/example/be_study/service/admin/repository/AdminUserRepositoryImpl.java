package com.example.be_study.service.admin.repository;

import com.example.be_study.service.admin.dto.AdminUserInfoDto;
import com.example.be_study.service.admin.dto.AdminUserListRequest;
import com.example.be_study.service.admin.dto.PagingRequest;
import com.example.be_study.service.user.domain.QUserMetric;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
}
