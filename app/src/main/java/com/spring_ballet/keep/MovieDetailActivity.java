package com.spring_ballet.keep;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.spring_ballet.keep.CommonUtils.ApiList;
import com.spring_ballet.keep.CommonUtils.IntentUtil;
import com.spring_ballet.keep.CommonUtils.MovieListType;
import com.spring_ballet.keep.CommonUtils.MovieUtils.AkaFormatUtil;
import com.spring_ballet.keep.CommonUtils.MovieUtils.CountryFormatUtil;
import com.spring_ballet.keep.CommonUtils.MyAsyncTask;
import com.spring_ballet.keep.CommonUtils.ToastUtil;
import com.spring_ballet.keep.bean.Movie;
import com.spring_ballet.keep.bean.MovieBean.Subjects;
import com.spring_ballet.keep.databinding.ActivityMovieDetailBinding;

import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {
    private ActivityMovieDetailBinding binding;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        setSupportActionBar(binding.toolbarMovieDetail);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        intent = getIntent();
        Glide.with(this).load(intent.getStringExtra("url")).into(binding.ivMovieDetailImg);
        binding.tvMovieDetailDir.setText(intent.getStringExtra("dir"));
        binding.tvMovieDetailCasts.setText(intent.getStringExtra("major"));
        binding.tvMovieDetailType.setText(intent.getStringExtra("type"));
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
        asyncTask.execute(ApiList.MovieItemInfo + intent.getStringExtra("id"));
        asyncTask.setOnAsyncResponse(new MyAsyncTask.AsyncResponse() {
            @Override
            public void onSubjectsDataReceivedSuccess(List<Subjects> subjects) {

            }

            @Override
            public void onMovieDataReceivedSuccess(Movie movie) {
                binding.setMovie(movie);
                binding.tvMovieDetailCountry.setText(CountryFormatUtil.format(movie.countries));
                binding.tvMovieDetailAlias.setText(AkaFormatUtil.format(movie.aka));
                binding.tvMovieDetailSummary.setText(movie.summary);
            }

            @Override
            public void onDataReceivedFailed() {
                ToastUtil.showToast(MovieDetailActivity.this, "加载出错了");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_more:
                IntentUtil.startIntent(MovieDetailActivity.this, MovieMobileActivity.class,
                        binding.getMovie().alt, binding.getMovie().title);
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
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
