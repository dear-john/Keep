package com.spring_ballet.keep.CommonUtils;


import android.content.Context;
import android.content.Intent;

import com.spring_ballet.keep.CommonUtils.MovieUtils.CastsFormatUtil;
import com.spring_ballet.keep.CommonUtils.MovieUtils.DirectorsFormatUtil;
import com.spring_ballet.keep.CommonUtils.MovieUtils.GenresFormatUtil;
import com.spring_ballet.keep.bean.MovieBean.Subjects;

public class IntentUtil {
    public static void startIntent(Context context, Class<?> c) {
        Intent intent = new Intent(context, c);
        context.startActivity(intent);
    }

    public static void startIntent(Context context, Class<?> c, String data) {
        Intent intent = new Intent(context, c);
        intent.putExtra("data", data);
        context.startActivity(intent);
    }

    public static void startIntent(Context context, Class<?> c, Subjects subjects) {
        Intent intent = new Intent(context, c);
        intent.putExtra("id", subjects.id);
        intent.putExtra("url", subjects.images.large);
        intent.putExtra("dir", DirectorsFormatUtil.format(subjects.directors));
        intent.putExtra("major", CastsFormatUtil.format(subjects.casts));
        intent.putExtra("type", GenresFormatUtil.format(subjects.genres));
        context.startActivity(intent);
    }

    public static void startIntent(Context context, Class<?> c, Subjects subjects, String dir, String major, String type) {
        Intent intent = new Intent(context, c);
        intent.putExtra("url", subjects.images.large);
        intent.putExtra("id", subjects.id);
        intent.putExtra("dir", dir);
        intent.putExtra("major", major);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }
}
