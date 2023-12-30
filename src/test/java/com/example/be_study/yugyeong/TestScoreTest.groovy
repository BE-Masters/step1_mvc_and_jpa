package com.example.be_study.yugyeong

import com.example.be_study.yugyeong.spock.TestScore
import spock.lang.Specification

class TestScoreTest extends Specification{

    def "시험 성적을 등급으로 바꾸기"() {
        given: // 시험 성적이 89점일 때
        int score = 89;

        when: // 메소드 호출
        def result = TestScore.testScoreToGrade(score);

        then: // 결과 값이 "B"일 때 테스트 성공, 이외는 실패
        result == "B";
    }
}
