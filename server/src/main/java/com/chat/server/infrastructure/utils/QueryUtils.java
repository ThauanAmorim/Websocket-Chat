package com.chat.server.infrastructure.utils;

import java.util.HashMap;
import java.util.Map;

public class QueryUtils {

    private static final String EQUALS_SYMBOL = "=";
    private static final String JOIN_SYMBOL = "&";
   
    private QueryUtils() {}

    public static Map<String, String> getMapFromString(String raw) {
        String[] values = raw.split(JOIN_SYMBOL);

        Map<String, String> map = new HashMap<>();
        String[] keyValueArray;
        for (String value : values) {
            keyValueArray = value.split(EQUALS_SYMBOL);
            map.put(keyValueArray[0], keyValueArray[1]);
        }
        return map;
    }

}
