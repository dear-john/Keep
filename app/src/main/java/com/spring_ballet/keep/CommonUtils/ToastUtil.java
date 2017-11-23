package com.spring_ballet.keep.CommonUtils;


import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    public static void showToast(Context context, String data) {
        Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
    }
}
