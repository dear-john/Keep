package com.spring_ballet.keep.fragment.Book;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spring_ballet.keep.Adapter.BookRecyclerViewAdapter;
import com.spring_ballet.keep.CommonUtils.ApiList;
import com.spring_ballet.keep.CommonUtils.DiffTypeNumber;
import com.spring_ballet.keep.CommonUtils.MyAsyncTask;
import com.spring_ballet.keep.CommonUtils.ToastUtil;
import com.spring_ballet.keep.R;
import com.spring_ballet.keep.bean.Book;
import com.spring_ballet.keep.bean.Movie;
import com.spring_ballet.keep.bean.MovieBean.Subjects;
import com.spring_ballet.keep.databinding.FragmentBookTypeBinding;

import java.util.List;

public class ShengHuoFragment extends Fragment {

    private boolean isFirst = true;
    private FragmentBookTypeBinding binding;
    private static Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_book_type, container, false);
        return binding.getRoot();
    }

    public static ShengHuoFragment newInstance(Context c) {
        context = c;
        return new ShengHuoFragment();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isFirst) {
            isFirst = false;
            MyAsyncTask myAsyncTask = new MyAsyncTask(DiffTypeNumber.BOOKTAG);
            myAsyncTask.execute(ApiList.SearchBookUrl + ApiList.BookSearchTags[2]);
            myAsyncTask.setOnAsyncResponse(new MyAsyncTask.AsyncResponse() {
                @Override
                public void onSubjectsDataReceivedSuccess(List<Subjects> subjects) {
                }

                @Override
                public void onMovieDataReceivedSuccess(Movie movie) {

                }

                @Override
                public void onBookDataReceivedSuccess(Book book) {
                    if (book != null) {
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
                        binding.recyclerviewBookType.setLayoutManager(gridLayoutManager);
                        binding.recyclerviewBookType.setAdapter(new BookRecyclerViewAdapter(context, book.books));
                    }
                }

                @Override
                public void onDataReceivedFailed() {
                    ToastUtil.showToast(context, "加载出错了");
                }
            });
        }
    }
}
