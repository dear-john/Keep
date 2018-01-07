package com.spring_ballet.keep;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.spring_ballet.keep.CommonUtils.ApiList;
import com.spring_ballet.keep.CommonUtils.DiffTypeNumber;
import com.spring_ballet.keep.CommonUtils.MovieLoadDataUtil;
import com.spring_ballet.keep.base.BaseActivity;
import com.spring_ballet.keep.databinding.ActivityTopMovieBinding;

public class TopMovieActivity extends BaseActivity<ActivityTopMovieBinding> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.ivTopMovieBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        MovieLoadDataUtil.loadData(ApiList.DouBanTop250, DiffTypeNumber.TOPMOVIE, binding.recyclerViewMovieTop, this);
    }

    @Override
    protected Toolbar getToolBar() {
        return binding.toolbarTopMovie;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_top_movie;
    }
}
