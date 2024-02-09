package com.example.be_study.service.user.dto;

import com.example.be_study.service.user.enums.DeviceType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserMetricPagingResponse {
    private Long userId;
    private short age;
    private DeviceType deviceType;


    @Builder
    public UserMetricPagingResponse(Long userId,
                                    short age,
                                    DeviceType deviceType) {
        this.userId = userId;
        this.age = age;
        this.deviceType = deviceType;
    }

}
