package com.example.be_study.yugyeong.jpa.dto.request;

import com.example.be_study.yugyeong.jpa.type.GenderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@AllArgsConstructor @NoArgsConstructor
public class StudentSignUpRequest {

    private int studentNumber;

    private String studentName;

    private GenderType studentGender;

    private String studentPhoneNumber;

}
