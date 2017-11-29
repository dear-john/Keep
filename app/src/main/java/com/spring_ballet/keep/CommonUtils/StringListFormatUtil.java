package com.spring_ballet.keep.CommonUtils;


import java.util.List;

public class StringListFormatUtil {
    public static String format(List<String> strings) {
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        for (String s : strings) {
            if (isFirst) {
                builder.append(s);
                isFirst = false;
            } else {
                builder.append("/").append(s);
            }
        }
        return builder.toString();
    }
}
