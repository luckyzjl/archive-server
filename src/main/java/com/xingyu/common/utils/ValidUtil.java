package com.xingyu.common.utils;

import java.util.regex.Pattern;


public class ValidUtil {

    public static boolean isMobile(String mobile,String REGEX_MOBILE) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    public static boolean isMatch(String matchstr,String REGEX){
        return Pattern.matches(REGEX,matchstr);
    }

}
