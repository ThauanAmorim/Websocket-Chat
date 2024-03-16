package com.chat.server.infrastructure.utils;

public class StringUtils {
    
    private StringUtils() {}

    public static boolean isBlank(String str) {
        return org.apache.commons.lang3.StringUtils.isBlank(str);
    }

    public static boolean notBlank(String str) {
        return org.apache.commons.lang3.StringUtils.isNotBlank(str);
    }

}
