package com.ua.alevel.gamesecond.brackets;

import ua.com.alevel.StringerUtil;

public final class ValidBrackets {

    private ValidBrackets() {}

    public static boolean isValidBracketsInStr(String str) {
       return StringerUtil.validationBrackets(str);
    }
}
