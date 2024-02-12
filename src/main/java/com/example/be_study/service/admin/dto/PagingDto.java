package com.example.be_study.service.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PagingDto {

    private int number; // 현재 페이지

    private int totalPages; // 전체 페이지수

    private long totalData; // 전체 데이터 개수

    public int getNumber() {
        return number + 1;
    }
}
