package com.example.be_study.service.admin.repository;

import com.example.be_study.service.admin.dto.AdminUserInfoDto;
import com.example.be_study.service.admin.dto.AdminUserListRequest;
import com.example.be_study.service.admin.dto.UserAgeCount;
import org.springframework.data.domain.Page;

public interface AdminUserCustomRepository {

    Page<AdminUserInfoDto> findAll(AdminUserListRequest request);

    UserAgeCount userAgeCount();
}
