package com.spring_ballet.keep.CommonUtils.MovieUtils;


import com.spring_ballet.keep.bean.MovieBean.Casts;

import java.util.List;

public class CastsFormatUtil {
    public static String format(List<Casts> casts) {
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        for (Casts c : casts) {
            if (isFirst) {
                builder.append(c.name);
                isFirst = false;
            } else {
                builder.append("/").append(c.name);
            }
        }
        return builder.toString();
    }
}
