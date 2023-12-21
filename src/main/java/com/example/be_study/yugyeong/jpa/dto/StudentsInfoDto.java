package com.example.be_study.yugyeong.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@AllArgsConstructor @NoArgsConstructor
public class StudentsInfoDto {

    private int studentNumber;

    private String studentName;

}
