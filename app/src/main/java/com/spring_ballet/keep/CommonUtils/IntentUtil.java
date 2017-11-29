package com.spring_ballet.keep.CommonUtils;


import android.content.Context;
import android.content.Intent;

public class IntentUtil {

    public static void startIntent(Context context, Class<?> c, String... data) {
        Intent intent = new Intent(context, c);
        for (int i = 0; i < data.length; i++) {
            intent.putExtra("data" + (i + 1), data[i]);
        }
        intent.putExtra("length", data.length + 1);
        context.startActivity(intent);
    }
}
