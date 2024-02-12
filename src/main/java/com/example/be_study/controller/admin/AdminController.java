package com.example.be_study.controller.admin;

import com.example.be_study.common.response.DataResponse;
import com.example.be_study.service.admin.annotation.AuthAdmin;
import com.example.be_study.service.admin.dto.AdminUserListRequest;
import com.example.be_study.service.admin.dto.AdminUserListResponse;
import com.example.be_study.service.admin.service.AdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @AuthAdmin
    @GetMapping(name = "관리자 확인")
    public String isAdmin() {
        return "오늘의 집 관리자입니다.";
    }

    /**
     *  사용자 목록 조회
     */
    @GetMapping(value = "/user-list", name = "사용자 목록 조회")
    public DataResponse<AdminUserListResponse> adminUserList(AdminUserListRequest request) {
        DataResponse<AdminUserListResponse> response = adminService.adminUserList(request);
        return response;
    }
}
