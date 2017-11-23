package com.spring_ballet.keep.CommonUtils;


import android.content.Context;
import android.content.Intent;

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
}
