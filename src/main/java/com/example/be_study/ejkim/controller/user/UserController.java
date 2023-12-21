package com.example.be_study.ejkim.controller.user;

import com.example.be_study.ejkim.service.user.dto.UserRequestDto;
import com.example.be_study.ejkim.service.user.service.UserSerive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "유저", value="유저 정보를 관리하는 컨트롤러")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserSerive userSerive;

    public UserController(UserSerive userSerive) {
        this.userSerive = userSerive;
    }

    @ApiOperation(value = "유저 이름 ", notes = "유저 아이디로 유저 이름 찾기")
    @PatchMapping(value = "/name/{userId}")
    public ResponseEntity<UserRequestDto> updateUserAttributes(@PathVariable Long userId) {
        return ResponseEntity.ok().body(userSerive.getUserName(userId));
    }

}
