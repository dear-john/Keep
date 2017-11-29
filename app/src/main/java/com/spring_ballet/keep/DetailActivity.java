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
import com.spring_ballet.keep.CommonUtils.DiffTypeNumber;
import com.spring_ballet.keep.CommonUtils.IntentUtil;
import com.spring_ballet.keep.CommonUtils.MyAsyncTask;
import com.spring_ballet.keep.CommonUtils.StringListFormatUtil;
import com.spring_ballet.keep.bean.Book;
import com.spring_ballet.keep.bean.Movie;
import com.spring_ballet.keep.bean.MovieBean.Subjects;
import com.spring_ballet.keep.databinding.ActivityDetailBinding;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    private String alt;
    private String title;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        setSupportActionBar(binding.toolbarDetail);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        binding.ivDetailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        loadData();
    }

    private void loadData() {
        Intent intent = getIntent();
        int length = intent.getIntExtra("length", 0);
        if (length != 0) {
            type = intent.getStringExtra("data1");
            if (type.equals("BOOK")) {
                String[] data = new String[length];
                for (int i = 2; i <= length; i++) {
                    data[i - 1] = intent.getStringExtra("data" + i);
                }
                title = data[1];
                binding.tvDetailTitle.setText(data[1]);
                binding.tvFirst.setText("作者: ");
                binding.tvDetailFirst.setText(data[2]);
                binding.tvSecond.setText("评分: ");
                String s = data[3] + " " + data[4] + "人评分";
                binding.tvDetailSecond.setText(s);
                binding.tvThird.setText("出版时间: ");
                binding.tvDetailThird.setText(data[5]);
                binding.tvFourth.setText("出版社: ");
                binding.tvDetailFourth.setText(data[6]);
                binding.layoutFirst.tvMovieList.setText("摘要");
                binding.tvDetailBodyFirst.setText(data[7]);
                binding.layoutSecond.tvMovieList.setText("作者简介");
                binding.tvDetailBodySecond.setText(data[8]);
                binding.tvFifth.setText("页数: ");
                binding.tvDetailFifth.setText(data[11]);
                binding.tvSix.setText("分类: ");
                binding.tvDetailSix.setText(data[12]);
                alt = data[9];
                Glide.with(this).load(data[10]).into(binding.ivDetailImg);
            } else if (type.equals("MOVIE")) {
                String[] data = new String[length];
                for (int i = 2; i <= length; i++) {
                    data[i - 1] = intent.getStringExtra("data" + i);
                }
                title = data[1];
                Glide.with(this).load(data[6]).into(binding.ivDetailImg);
                binding.tvDetailTitle.setText(data[1]);
                binding.tvFirst.setText("导演: ");
                binding.tvDetailFirst.setText(data[2]);
                binding.tvSecond.setText("主演: ");
                binding.tvDetailSecond.setText(data[3]);
                binding.tvThird.setText("类型: ");
                binding.tvDetailThird.setText(data[4]);
                loadMovieData(data[5]);
            }
        }
    }

    private void loadMovieData(String id) {
        MyAsyncTask asyncTask = new MyAsyncTask(DiffTypeNumber.MOVIEDETAIL);
        asyncTask.execute(ApiList.MovieItemInfo + id);
        asyncTask.setOnAsyncResponse(new MyAsyncTask.AsyncResponse() {
            @Override
            public void onSubjectsDataReceivedSuccess(List<Subjects> subjects) {

            }

            @Override
            public void onMovieDataReceivedSuccess(Movie movie) {
                alt = movie.alt;
                binding.tvFourth.setText("上映日期: ");
                binding.tvDetailFourth.setText(String.valueOf(movie.year));
                binding.tvFifth.setText("制片国家/地区: ");
                binding.tvDetailFifth.setText(StringListFormatUtil.format(movie.countries));
                binding.tvSix.setText("评分: ");
                String s = movie.rating.average + " " + movie.ratings_count + "人评分";
                binding.tvDetailSix.setText(s);
                binding.layoutFirst.tvMovieList.setText("另称: ");
                binding.tvDetailBodyFirst.setText(StringListFormatUtil.format(movie.aka));
                binding.layoutSecond.tvMovieList.setText("剧情简介: ");
                binding.tvDetailBodySecond.setText(movie.summary);
            }

            @Override
            public void onBookDataReceivedSuccess(Book book) {

            }

            @Override
            public void onDataReceivedFailed() {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.see_more_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_more:
                IntentUtil.startIntent(DetailActivity.this, MobileActivity.class, type, alt, title);
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
