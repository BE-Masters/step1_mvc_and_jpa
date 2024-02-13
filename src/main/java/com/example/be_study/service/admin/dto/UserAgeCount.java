package com.example.be_study.service.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserAgeCount {

    private int twenties;

    private int thirties;

    private int forties;

    private int fifties;

    private int sixties;

    private int seventies;

    private int eighties;

    private int nineties;

    public UserAgeCount(int twenties, int thirties, int forties, int fifties, int sixties, int seventies, int eighties, int nineties) {
        this.twenties = twenties;
        this.thirties = thirties;
        this.forties = forties;
        this.fifties = fifties;
        this.sixties = sixties;
        this.seventies = seventies;
        this.eighties = eighties;
        this.nineties = nineties;
    }
}
