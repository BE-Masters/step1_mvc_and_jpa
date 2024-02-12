package com.example.be_study.service.admin.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class AdminUserListResponse {

    private PagingDto pagingDto;

    private List<AdminUserInfoDto> userList;

    @Builder
    public AdminUserListResponse(PagingDto pagingDto, List<AdminUserInfoDto> userList) {
        this.pagingDto = pagingDto;
        this.userList = userList;
    }

    public static AdminUserListResponse of(PagingDto pagingDto, List<AdminUserInfoDto> userList) {
        return AdminUserListResponse.builder()
                .pagingDto(pagingDto)
                .userList(userList)
                .build();
    }
}
