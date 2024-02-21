package com.example.be_study.service.admin.repository;

import com.example.be_study.service.admin.dto.AdminUserInfoDto;
import com.example.be_study.service.admin.dto.UserAgeCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminUserCustomRepository {

    Page<AdminUserInfoDto> findAll(Pageable request);

    UserAgeCount userAgeCount();
}
