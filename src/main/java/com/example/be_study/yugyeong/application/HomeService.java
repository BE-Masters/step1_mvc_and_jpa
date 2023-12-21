package com.example.be_study.yugyeong.application;

import com.example.be_study.yugyeong.dto.UserProfileRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *  Model - 비즈니스 로직 처리
 */
@Service
@RequiredArgsConstructor
public class HomeService {

    public UserProfileRequest userProfile() { // 사용자 프로필 데이터 생성
        return UserProfileRequest.builder()
                .userName("홍길동")
                .userPhoneNumber("01012345678")
                .userAge(24)
                .build();
    }

}
