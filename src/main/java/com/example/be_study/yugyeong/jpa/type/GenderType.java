package com.example.be_study.yugyeong.jpa.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderType {

    M(1, "성별", "남성"),

    W(2, "성별", "여성");

    private final int id;

    private final String description;

    private final String value;

}
