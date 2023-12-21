package com.example.be_study.ejkim.service.user.dto;

import com.example.be_study.ejkim.service.user.domain.User;

import lombok.Getter;

@Getter
public class UserRequestDto {
    private String name;

    public User toUser(){
        return User.builder()
                .name(getName())
                .build();
    }
}
