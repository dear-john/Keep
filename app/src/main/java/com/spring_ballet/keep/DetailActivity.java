package com.spring_ballet.keep;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.spring_ballet.keep.CommonUtils.ApiList;
import com.spring_ballet.keep.CommonUtils.DiffTypeNumber;
import com.spring_ballet.keep.CommonUtils.IntentUtil;
import com.spring_ballet.keep.CommonUtils.MovieUtils.CastsFormatUtil;
import com.spring_ballet.keep.CommonUtils.MovieUtils.DirectorsFormatUtil;
import com.spring_ballet.keep.CommonUtils.MyAsyncTask;
import com.spring_ballet.keep.CommonUtils.StringListFormatUtil;
import com.spring_ballet.keep.CommonUtils.TagFormatUtil;
import com.spring_ballet.keep.base.BaseActivity;
import com.spring_ballet.keep.base.BaseClass;
import com.spring_ballet.keep.bean.BookBean.Books;
import com.spring_ballet.keep.bean.Movie;
import com.spring_ballet.keep.bean.MovieBean.Subjects;
import com.spring_ballet.keep.databinding.ActivityDetailBinding;

import java.util.List;

public class DetailActivity extends BaseActivity<ActivityDetailBinding> {
    private String alt;
    private String title;
    private String type;
    private static Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.ivDetailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        loadData();
//        handler = new Handler(){
//            WeakReference<Activity> activity;
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                //UI
//                if (activity!=null);
//            }
//        };
//
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//
//
//                Message m = handler.obtainMessage(1);
//
//                handler.sendMessageDelayed(m,1000);
//            }
//        }.start();
    }

    @Override
    protected Toolbar getToolBar() {
        return binding.toolbarDetail;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_detail;
    }

    private void loadData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Bundle");
        BaseClass baseClass = bundle.getParcelable("BaseClass");
        if (baseClass instanceof Subjects) {
            Subjects subjects = (Subjects) baseClass;
            title = subjects.getTitle();
            type = "MOVIE";
            Glide.with(this).load(subjects.getImages().getLarge()).into(binding.ivDetailImg);
            binding.tvDetailTitle.setText(subjects.getTitle());
            binding.tvFirst.setText("导演: ");
            binding.tvDetailFirst.setText(DirectorsFormatUtil.format(subjects.getDirectors()));
            binding.tvSecond.setText("主演: ");
            binding.tvDetailSecond.setText(CastsFormatUtil.format(subjects.getCasts()));
            binding.tvThird.setText("类型: ");
            binding.tvDetailThird.setText(StringListFormatUtil.format(subjects.getGenres()));
            binding.tvFourth.setText("上映日期: ");
            binding.tvFifth.setText("制片国家/地区: ");
            binding.tvSix.setText("评分: ");
            loadMovieData(subjects.getId());
        } else if (baseClass instanceof Books) {
            Books books = (Books) baseClass;
            title = books.getTitle();
            type = "BOOK";
            alt = books.getAlt();
            binding.tvDetailTitle.setText(books.getTitle());
            binding.tvFirst.setText("作者: ");
            binding.tvDetailFirst.setText(StringListFormatUtil.format(books.getAuthor()));
            binding.tvSecond.setText("评分: ");
            String s = books.getRating().getAverage() + " " + books.getRating().getNumRaters() + "人评分";
            binding.tvDetailSecond.setText(s);
            binding.tvThird.setText("出版时间: ");
            binding.tvDetailThird.setText(books.getPubdate());
            binding.tvFourth.setText("出版社: ");
            binding.tvDetailFourth.setText(books.getPublisher());
            binding.layoutFirst.tvMovieList.setText("摘要");
            binding.tvDetailBodyFirst.setText(books.getSummary());
            binding.layoutSecond.tvMovieList.setText("作者简介");
            binding.tvDetailBodySecond.setText(books.getAuthor_intro());
            binding.tvFifth.setText("页数: ");
            binding.tvDetailFifth.setText(String.valueOf(books.getPages()));
            binding.tvSix.setText("分类: ");
            binding.tvDetailSix.setText(TagFormatUtil.format(books.getTags()));
            Glide.with(this).load(books.getImages().getLarge()).into(binding.ivDetailImg);
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
            public void onOtherDataReceivedSuccess(BaseClass baseClass) {
                if (baseClass instanceof Movie) {
                    Movie movie = (Movie) baseClass;
                    alt = movie.getAlt();
                    binding.tvDetailFourth.setText(String.valueOf(movie.getYear()));
                    binding.tvDetailFifth.setText(StringListFormatUtil.format(movie.getCountries()));
                    String s = movie.getRating().getAverage() + " " + movie.getRatings_count() + "人评分";
                    binding.tvDetailSix.setText(s);
                    binding.layoutFirst.tvMovieList.setText("另称: ");
                    binding.tvDetailBodyFirst.setText(StringListFormatUtil.format(movie.getAka()));
                    binding.layoutSecond.tvMovieList.setText("剧情简介: ");
                    binding.tvDetailBodySecond.setText(movie.getSummary());
                }
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

}
