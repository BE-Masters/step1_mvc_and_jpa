package com.example.be_study.service.user.service;

import com.example.be_study.service.user.domain.UserMetric;
import com.example.be_study.service.user.repository.UserMetricRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

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

    public Page<UserMetric> getUserMetricPage(Pageable pageable){
        return userMetricRepository.findAll(pageable);
    }

    public Map<String, Long> getUserAgeGroup(){
        return userMetricRepository.getUserAgeGroup();
    }
}
