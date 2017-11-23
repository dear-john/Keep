package com.spring_ballet.keep.CommonUtils;


import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtil {

    private static String data = null;

    public static String getData(final String url) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            data = response.body().string();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return data;
    }
}
