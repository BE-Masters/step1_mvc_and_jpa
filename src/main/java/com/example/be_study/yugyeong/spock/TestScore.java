package com.example.be_study.yugyeong.spock;

public class TestScore {

    /**
     * 시험 성적을 조건에 맞게 등급으로 출력
     */
    public static String testScoreToGrade(int score) {
        String grade;

        if (score >= 90) grade = "A";
        else if (score >= 80) grade = "B";
        else if (score >= 70) grade = "C";
        else grade = "F";

        return grade;
    }
}
