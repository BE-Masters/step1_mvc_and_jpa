package com.example.be_study.service.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AdminUserListRequest {

    private int page; // 요청 페이지

    private int size; // 한 페이지 당 보여질 개수
}
