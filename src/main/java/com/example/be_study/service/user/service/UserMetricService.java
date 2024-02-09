package com.example.be_study.service.user.service;

import com.example.be_study.service.user.domain.UserMetric;
import com.example.be_study.service.user.dto.UserMetricPagingResponse;
import com.example.be_study.service.user.repository.UserMetricRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Dictionary;

@Service
public class UserMetricService {
    public UserMetricRepository userMetricRepository;

    public UserMetricService(UserMetricRepository userMetricRepository) {
        this.userMetricRepository = userMetricRepository;
    }

    @Transactional(readOnly = false)
    public void createVeryManyUser() {

        userMetricRepository.saveAllUserBulkMode();
    }

    public Page<UserMetricPagingResponse> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 50; // 한 페이지에 보여줄 데이터 수

        Page<UserMetric> userMetricPage = userMetricRepository.findAll(PageRequest.of(page, pageLimit,
                Sort.by(Sort.Direction.ASC,"userId")));

        Page<UserMetricPagingResponse> userMetricPagingResponsesDto = userMetricPage.map(
                userMetric -> new UserMetricPagingResponse(
                        userMetric.getUserId(),
                        userMetric.getAge(),
                        userMetric.getDeviceType()));

        return userMetricPagingResponsesDto;
    }


}
