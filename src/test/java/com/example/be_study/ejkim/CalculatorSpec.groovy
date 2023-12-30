package com.example.be_study.ejkim

import spock.lang.Specification

class CalculatorSpec extends Specification{
    def "덧셈 테스트"(){
        given: "계산기 인스턴스"
        Calculator calculator = new Calculator()

        when : "두 수를 더함"
        int result = calculator.add(500,2)

        then: "두 수를 합친 결과가 나와야 함"
        result == 502
    }
}
