package com.spring_ballet.keep.CommonUtils;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.spring_ballet.keep.bean.BookBean.Books;
import com.spring_ballet.keep.bean.MovieBean.Subjects;

public class IntentUtil {

    public static void startIntent(Context context, Class<?> c, String... data) {
        Intent intent = new Intent(context, c);
        for (int i = 0; i < data.length; i++) {
            intent.putExtra("data" + (i + 1), data[i]);
        }
        intent.putExtra("length", data.length + 1);
        context.startActivity(intent);
    }

    public static void startIntent(Context context, Class<?> c, Books books, Subjects subjects) {
        Intent intent = new Intent(context, c);
        Bundle bundle = new Bundle();
        if (books != null) {
            bundle.putParcelable("BaseClass", books);
        } else if (subjects != null) {
            bundle.putParcelable("BaseClass", subjects);
        }
        intent.putExtra("Bundle", bundle);
        context.startActivity(intent);
    }
}
