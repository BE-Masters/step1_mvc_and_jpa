package com.example.be_study.utils;

import com.example.be_study.service.user.domain.UserPrincipal;
import com.example.be_study.service.user.enums.UserType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("UserCheckUtils")
public class UserCheckUtils {
    public boolean isAdmin() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return UserType.ADMIN.equals(userPrincipal.getUser().getUserType());
    }
}
