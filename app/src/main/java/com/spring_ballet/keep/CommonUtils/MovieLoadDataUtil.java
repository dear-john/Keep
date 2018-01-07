package com.spring_ballet.keep.CommonUtils;


import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.spring_ballet.keep.Adapter.MovieRecyclerViewAdapter;
import com.spring_ballet.keep.base.BaseClass;
import com.spring_ballet.keep.bean.Event;
import com.spring_ballet.keep.bean.MovieBean.Subjects;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MovieLoadDataUtil {

    public static void loadData(final String url, final int type, final RecyclerView recyclerView, final Context context) {
        MyAsyncTask myAsyncTask = new MyAsyncTask(type);
        myAsyncTask.execute(url);
        myAsyncTask.setOnAsyncResponse(new MyAsyncTask.AsyncResponse() {
            @Override
            public void onSubjectsDataReceivedSuccess(List<Subjects> subjects) {
                if (subjects != null) {
                    EventBus.getDefault().post(new Event(true));
                    LinearLayoutManager manager = new LinearLayoutManager(context,
                            LinearLayoutManager.HORIZONTAL, false);
                    switch (type) {
                        case DiffTypeNumber.HOTMOVIE:
                        case DiffTypeNumber.COMINGSOON:
                        case DiffTypeNumber.USBOX:
                            recyclerView.setLayoutManager(manager);
                            recyclerView.setAdapter(new MovieRecyclerViewAdapter(context, subjects));
                            break;
                        case DiffTypeNumber.TOPMOVIE:
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
                            recyclerView.setLayoutManager(gridLayoutManager);
                            recyclerView.setAdapter(new MovieRecyclerViewAdapter(context, subjects));
                            break;
                        default:
                    }
                }
            }

            @Override
            public void onOtherDataReceivedSuccess(BaseClass baseClass) {

            }

            @Override
            public void onDataReceivedFailed() {
                ToastUtil.showToast(context, "加载出错了");
            }
        });
    }
}
