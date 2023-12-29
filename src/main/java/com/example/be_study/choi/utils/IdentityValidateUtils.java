package com.example.be_study.choi.utils;

import org.springframework.util.ObjectUtils;

public class IdentityValidateUtils {

    public static int END_IDENTITY_FIRST_WORD_INDEX = 6;
    public static int IDENTITY_SIZE = 13;
    //REGEX
    public static String REGEX_ALL_NUMBER = "[0-9]+"; //전부 숫자인지 검증합니다.

    //Error Message
    public static String ERROR_NULL_OR_EMPTY_IDENTITY = "주민등록번호가 Null이거나 비어있습니다.";
    public static String ERROR_LENGTH_IDENTITY = "주민등록번호 자릿수가 올바르지 않습니다.";
    public static String ERROR_CONTAIN_CHARACTER = "주민등록번호에 숫자가 아닌 값이 섞여있습니다.";
    public static String ERROR_BAD_END_FIRST_WORD = "주민등록번호의 정보가 잘못됬습니다 - 내국인 외국인인지의 범위에 하나도 포함되지 않음";

    public static void validationIdentity(String identity) {
        if (ObjectUtils.isEmpty(identity)) {
            throw new IllegalArgumentException(ERROR_NULL_OR_EMPTY_IDENTITY);
        }
        if (IDENTITY_SIZE != identity.length()) {
            throw new IllegalArgumentException(ERROR_LENGTH_IDENTITY);
        }
        if (!identity.matches(REGEX_ALL_NUMBER)) {
            throw new IllegalArgumentException(ERROR_CONTAIN_CHARACTER);
        }

        int firstWord = (int) identity.charAt(END_IDENTITY_FIRST_WORD_INDEX) - '0';

        if (firstWord < 0 || firstWord > 8) {
            throw new IllegalArgumentException(ERROR_BAD_END_FIRST_WORD);
        }
    }

    public static boolean isIdentityNationalUser(String identity) {
        validationIdentity(identity);
        int firstWord = (int) identity.charAt(END_IDENTITY_FIRST_WORD_INDEX) - '0';
        return (firstWord > 0 && firstWord < 5);
    }

    public static boolean isIdentityForeignerUser(String identity) {
        validationIdentity(identity);
        int firstWord = (int) identity.charAt(END_IDENTITY_FIRST_WORD_INDEX) - '0';
        return (firstWord > 4 && firstWord < 9);
    }

}
