package com.spring_ballet.keep.CommonUtils;


import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetBingDayPic {

    private static String data;

    public static String getBingDayPicUrl() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("http://guolin.tech/api/bing_pic").build();
                    Response response = client.newCall(request).execute();
                    data = response.body().string();
                } catch (IOException | NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        if (!TextUtils.isEmpty(data)) {
            return data;
        }
        return null;
    }
}
