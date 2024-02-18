package com.example.be_study.controller.user;

import com.example.be_study.common.response.DataResponse;
import com.example.be_study.security.CurrentUser;
import com.example.be_study.service.user.domain.UserMetric;
import com.example.be_study.service.user.dto.UserPrincipalResponse;
import com.example.be_study.service.user.enums.OauthResponseCode;
import com.example.be_study.service.user.repository.AdminOnly;
import com.example.be_study.service.user.repository.UserPrincipal;
import com.example.be_study.service.user.service.UserMetricService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserMissionTestController {

    private final UserMetricService userMetricService;


    public UserMissionTestController(UserMetricService userMetricService) {
        this.userMetricService = userMetricService;
    }

    @AdminOnly
    @GetMapping("/info")
    public DataResponse<UserPrincipalResponse> getUserInfo(@CurrentUser UserPrincipal userPrincipal){
        UserPrincipalResponse response = UserPrincipalResponse.of(userPrincipal);
        return new DataResponse<>(OauthResponseCode.OAUTH_AUTHORIZE_SUCCESS, response);
    }

    @GetMapping("/paging")
    public Page<UserMetric> getUserPaging(@PageableDefault(page = 1, size=10, sort = "userId",direction = Sort.Direction.ASC) Pageable pageable){
        return userMetricService.paging(pageable);
    }

    @GetMapping("/count-by-age-group")
    public ResponseEntity<Map<String, Long>> getUserByAge(){
        Map<String, Long> ageGroupCount = userMetricService.countUsersByAgeGroup();
        return ResponseEntity.ok(ageGroupCount);
    }

}
