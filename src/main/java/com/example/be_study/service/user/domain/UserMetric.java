package com.example.be_study.service.user.domain;

import com.example.be_study.service.user.enums.DeviceType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "user_metric")
@NoArgsConstructor
public class UserMetric {

    @Id
    private Long userId;

    @Column(name = "age", nullable = true)
    private short age;

    @Enumerated(EnumType.STRING)
    @Column(name = "device_type", nullable = true)
    private DeviceType deviceType;

    @Builder
    public UserMetric(Long userId, short age, DeviceType deviceType) {
        this.userId = userId;
        this.age = age;
        this.deviceType = deviceType;
    }
}
