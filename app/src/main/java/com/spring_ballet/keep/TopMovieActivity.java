package com.spring_ballet.keep;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.spring_ballet.keep.Adapter.MyRecyclerViewAdapter;
import com.spring_ballet.keep.CommonUtils.ApiList;
import com.spring_ballet.keep.CommonUtils.MovieListType;
import com.spring_ballet.keep.CommonUtils.MyAsyncTask;
import com.spring_ballet.keep.CommonUtils.ToastUtil;
import com.spring_ballet.keep.bean.Movie;
import com.spring_ballet.keep.bean.MovieBean.Subjects;
import com.spring_ballet.keep.databinding.ActivityTopMovieBinding;

import java.util.List;

public class TopMovieActivity extends AppCompatActivity {

    private ActivityTopMovieBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_top_movie);
        setSupportActionBar(binding.toolbarTopMovie);
        binding.ivTopMovieBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        loadData();
    }

    private void loadData() {
        MyAsyncTask myAsyncTask = new MyAsyncTask(MovieListType.TOPMOVIE);
        myAsyncTask.execute(ApiList.DouBanTop250);
        myAsyncTask.setOnAsyncResponse(new MyAsyncTask.AsyncResponse() {
            @Override
            public void onSubjectsDataReceivedSuccess(List<Subjects> subjects) {
                if (subjects != null) {
                    GridLayoutManager manager = new GridLayoutManager(TopMovieActivity.this, 3);
                    binding.recyclerViewMovieTop.setLayoutManager(manager);
                    binding.recyclerViewMovieTop.setAdapter(new MyRecyclerViewAdapter(TopMovieActivity.this, subjects));
                }
            }

            @Override
            public void onMovieDataReceivedSuccess(Movie movie) {

            }

            @Override
            public void onDataReceivedFailed() {
                ToastUtil.showToast(TopMovieActivity.this,"加载出错了");
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
