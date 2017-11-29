package com.spring_ballet.keep.CommonUtils;


import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.spring_ballet.keep.Adapter.MyRecyclerViewAdapter;
import com.spring_ballet.keep.bean.Book;
import com.spring_ballet.keep.bean.Movie;
import com.spring_ballet.keep.bean.MovieBean.Subjects;

import java.util.List;

public class BookLoadDataUtil {
    public static void loadData(final String url, final int type, final RecyclerView recyclerView, final Context context) {
        MyAsyncTask myAsyncTask = new MyAsyncTask(type);
        myAsyncTask.execute(url);
        myAsyncTask.setOnAsyncResponse(new MyAsyncTask.AsyncResponse() {
            @Override
            public void onSubjectsDataReceivedSuccess(List<Subjects> subjects) {
                if (subjects != null) {
                    LinearLayoutManager manager = new LinearLayoutManager(context,
                            LinearLayoutManager.HORIZONTAL, false);
                    switch (type) {
                        case DiffTypeNumber.HOTMOVIE:
                        case DiffTypeNumber.COMINGSOON:
                        case DiffTypeNumber.USBOX:
                            recyclerView.setLayoutManager(manager);
                            recyclerView.setAdapter(new MyRecyclerViewAdapter(context, subjects, CommonField.MOVIE_GENERAL_ITEM));
                            break;
                        case DiffTypeNumber.TOPMOVIE:
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
                            recyclerView.setLayoutManager(gridLayoutManager);
                            recyclerView.setAdapter(new MyRecyclerViewAdapter(context, subjects, CommonField.MOVIE_GENERAL_ITEM));
                            break;
                        case DiffTypeNumber.KOUBEI:
                            break;
                        case DiffTypeNumber.NEWMOVIE:
                            break;
                        default:
                    }
                }
            }

            @Override
            public void onMovieDataReceivedSuccess(Movie movie) {

            }

            @Override
            public void onBookDataReceivedSuccess(Book book) {

            }

            @Override
            public void onDataReceivedFailed() {
                ToastUtil.showToast(context, "加载出错了");
            }
        });
    }
}
