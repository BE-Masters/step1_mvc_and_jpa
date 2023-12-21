package com.example.be_study.yugyeong.dto;

import lombok.*;

@Getter @Builder
@AllArgsConstructor @NoArgsConstructor
public class UserProfileRequest {

    private String userName;

    private String userPhoneNumber;

    private int userAge;

}
