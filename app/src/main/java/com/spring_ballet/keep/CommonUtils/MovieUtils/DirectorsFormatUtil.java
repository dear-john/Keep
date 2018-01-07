package com.spring_ballet.keep.CommonUtils.MovieUtils;


import com.spring_ballet.keep.bean.MovieBean.Directors;

import java.util.List;

public class DirectorsFormatUtil {
    public static String format(List<Directors> directors) {
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        for (Directors d : directors) {
            if (isFirst) {
                builder.append(d.getName());
                isFirst = false;
            } else {
                builder.append("/").append(d.getName());
            }
        }
        return builder.toString();
    }
}
