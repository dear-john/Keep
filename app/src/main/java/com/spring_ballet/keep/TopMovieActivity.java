package com.spring_ballet.keep;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.spring_ballet.keep.CommonUtils.ApiList;
import com.spring_ballet.keep.CommonUtils.LoadDataUtil;
import com.spring_ballet.keep.CommonUtils.MovieListType;
import com.spring_ballet.keep.databinding.ActivityTopMovieBinding;

public class TopMovieActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTopMovieBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_top_movie);
        setSupportActionBar(binding.toolbarTopMovie);
        binding.ivTopMovieBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        LoadDataUtil.loadData(ApiList.DouBanTop250, MovieListType.TOPMOVIE, binding.recyclerViewMovieTop, this);
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
