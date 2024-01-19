package com.example.be_study.controller.user;

import com.example.be_study.common.response.DataResponse;
import com.example.be_study.service.user.domain.User;
import com.example.be_study.service.user.enums.OauthServerType;
import com.example.be_study.service.user.service.OauthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("/api/v1/oauth")
@RestController
public class OauthController {
    private final OauthService oauthService;

    public OauthController(OauthService oauthService) {
        this.oauthService = oauthService;
    }

    @SneakyThrows
    @GetMapping("/{oauthServerType}")
    public ResponseEntity<DataResponse<Void>> redirectAuthCodeRequestUrl(
            @PathVariable OauthServerType oauthServerType,
            HttpServletResponse response
    ) {
        try {
            String redirectUrl = oauthService.getAuthCodeRequestUrl(oauthServerType);
            response.sendRedirect(redirectUrl);
            return ResponseEntity.ok(new DataResponse<>(200, "successful", null));
        } catch (IOException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new DataResponse<>(500, "Redirect failed: " + e.getMessage(), null));
        }
    }

    @GetMapping("/login/{oauthServerType}/{code}")
    public DataResponse<User> login(
            @PathVariable("oauthServerType") OauthServerType oauthServerType,
            @PathVariable("code") String code
    ) {
        return new DataResponse<>(200, "success", oauthService.login(oauthServerType, code));
    }

}
