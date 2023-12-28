package com.example.be_study.choi.utils

import spock.lang.Specification

class IdentityValidateUtilsTests extends Specification {

    def "주민등록번호가 내국인이여합니다"() {
        given:
        String identity = "9302251111111";

        when:
        def result =  IdentityValidateUtils.isIdentityNationalUser(identity);

        then:
        !result
    }
}
