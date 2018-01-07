package com.spring_ballet.keep.CommonUtils;


import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.spring_ballet.keep.R;

public class GlideUtil {
    public static void glideUtil(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .setDefaultRequestOptions(new RequestOptions()
                        .placeholder(R.drawable.load_err)
                        .error(R.drawable.load_err))
                .load(url)
                .into(imageView);
    }
}
