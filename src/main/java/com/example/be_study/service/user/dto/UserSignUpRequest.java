package com.example.be_study.service.user.dto;

import com.example.be_study.service.policy.enums.PolicyType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class UserSignUpRequest {

    @NotBlank(message = "이메일은 필수입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    private String userEmail;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상 입력해야 됩니다.")
    @Pattern(regexp = "\\S+$", message = "공백은 사용할 수 없습니다.")
    private String userPassword;

    @NotBlank(message = "닉네임은 필수입니다.")
    @Size(min = 2, max = 15, message = "닉네임은 최소 2자 이상 최대 15자까지 입력해야 됩니다.")
    private String userNickname;

    @NotNull(message = "약관 동의는 필수입니다.")
    private List<PolicyType> policyTypeList;

    public UserSignUpRequest(String userEmail, String userPassword, String userNickname, List<PolicyType> policyTypeList) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userNickname = userNickname;
        this.policyTypeList = policyTypeList;
    }
}
