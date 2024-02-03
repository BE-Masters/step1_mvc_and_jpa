package com.example.be_study.service.user.service;

import com.example.be_study.service.user.repository.UserMetricRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
