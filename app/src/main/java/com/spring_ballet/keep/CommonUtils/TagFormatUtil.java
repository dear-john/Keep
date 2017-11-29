package com.spring_ballet.keep.CommonUtils;


import com.spring_ballet.keep.bean.BookBean.Tag;

import java.util.List;

public class TagFormatUtil {
    public static String format(List<Tag> tags) {
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        for (Tag t : tags) {
            if (isFirst) {
                builder.append(t.name);
                isFirst = false;
            } else {
                builder.append("/").append(t.name);
            }
        }
        return builder.toString();
    }
}
