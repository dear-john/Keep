package com.spring_ballet.keep;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.bumptech.glide.Glide;
import com.spring_ballet.keep.CommonUtils.ApiList;
import com.spring_ballet.keep.CommonUtils.MovieListType;
import com.spring_ballet.keep.CommonUtils.MyAsyncTask;
import com.spring_ballet.keep.CommonUtils.ToastUtil;
import com.spring_ballet.keep.bean.Movie;
import com.spring_ballet.keep.bean.MovieBean.Casts;
import com.spring_ballet.keep.bean.MovieBean.Directors;
import com.spring_ballet.keep.bean.MovieBean.Subjects;
import com.spring_ballet.keep.databinding.ActivityMovieDetailBinding;

import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {
    private ActivityMovieDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        setSupportActionBar(binding.toolbarMovieDetail);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        binding.layoutAlias.tvMovieList.setText("另称");
        binding.layoutSummary.tvMovieList.setText("剧情简介");
        binding.layoutMan.tvMovieList.setText("导演 & 演员");
        binding.ivMovieDetailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        loadData();
    }

    private void loadData() {
        MyAsyncTask asyncTask = new MyAsyncTask(MovieListType.MOVIEDETAIL);
        asyncTask.execute(ApiList.MovieItemInfo + getIntent().getStringExtra("data"));
        asyncTask.setOnAsyncResponse(new MyAsyncTask.AsyncResponse() {
            @Override
            public void onSubjectsDataReceivedSuccess(List<Subjects> subjects) {

            }

            @Override
            public void onMovieDataReceivedSuccess(Movie movie) {
                binding.setMovie(movie);
                Glide.with(MovieDetailActivity.this).load(movie.images.large).into(binding.ivMovieDetailImg);
                //导演
                StringBuilder builder1 = new StringBuilder();
                boolean isFirst = true;
                for (Directors d : movie.directors) {
                    if (isFirst) {
                        builder1.append(d.name);
                        isFirst = false;
                    } else {
                        builder1.append(" ").append("/").append(" ").append(d.name);
                    }
                }
                binding.tvMovieDetailDir.setText(builder1.toString());
                //主演
                StringBuilder builder2 = new StringBuilder();
                isFirst = true;
                for (Casts c : movie.casts) {
                    if (isFirst) {
                        builder2.append(c.name);
                        isFirst = false;
                    } else {
                        builder2.append(" ").append("/").append(" ").append(c.name);
                    }
                }
                binding.tvMovieDetailCasts.setText(builder2.toString());
                //电影类型
                StringBuilder builder3 = new StringBuilder();
                isFirst = true;
                for (String s : movie.genres) {
                    if (isFirst) {
                        builder3.append(s);
                        isFirst = false;
                    } else {
                        builder3.append(" ").append("/").append(" ").append(s);
                    }
                }
                binding.tvMovieDetailType.setText(builder3.toString());
                //制片地区
                StringBuilder builder4 = new StringBuilder();
                isFirst = true;
                for (String s : movie.countries) {
                    if (isFirst) {
                        builder4.append(s);
                        isFirst = false;
                    } else {
                        builder4.append(" ").append("/").append(" ").append(s);
                    }
                }
                binding.tvMovieDetailCountry.setText(builder4.toString());
                //另称
                StringBuilder builder5 = new StringBuilder();
                isFirst = true;
                for (String s : movie.aka) {
                    if (isFirst) {
                        builder5.append(s);
                        isFirst = false;
                    } else {
                        builder5.append(" ").append("/").append(" ").append(s);
                    }
                }
                binding.tvMovieDetailAlias.setText(builder5.toString());
                binding.tvMovieDetailSummary.setText(movie.summary);
            }

            @Override
            public void onDataReceivedFailed() {
                ToastUtil.showToast(MovieDetailActivity.this, "加载出错了");
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
