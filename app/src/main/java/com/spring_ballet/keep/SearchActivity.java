package com.spring_ballet.keep;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.spring_ballet.keep.Adapter.MyFragmentPagerAdapter;
import com.spring_ballet.keep.CommonUtils.ApiList;
import com.spring_ballet.keep.CommonUtils.DiffTypeNumber;
import com.spring_ballet.keep.CommonUtils.MyAsyncTask;
import com.spring_ballet.keep.CommonUtils.ToastUtil;
import com.spring_ballet.keep.base.BaseActivity;
import com.spring_ballet.keep.base.BaseClass;
import com.spring_ballet.keep.bean.MovieBean.Subjects;
import com.spring_ballet.keep.databinding.ActivitySearchBinding;
import com.spring_ballet.keep.fragment.SearchFragment.SearchBookFragment;
import com.spring_ballet.keep.fragment.SearchFragment.SearchMovieFragment;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity<ActivitySearchBinding> {

    private List<Subjects> subjectsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.ivSearchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new SearchBookFragment());
        fragmentList.add(new SearchMovieFragment());
        List<String> titleList = new ArrayList<>();
        titleList.add("书籍");
        titleList.add("电影");
        binding.layoutSearch.viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, titleList));
        binding.layoutSearch.viewPager.setOffscreenPageLimit(2);
        binding.layoutSearch.viewPager.setCurrentItem(0);
        binding.layoutSearch.tabLayout.setupWithViewPager(binding.layoutSearch.viewPager);
        binding.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    MyAsyncTask myAsyncTask = new MyAsyncTask(DiffTypeNumber.SEARCHMOVIE);
                    myAsyncTask.execute(ApiList.DouBanMovieSearchUrl + binding.etSearch.getText().toString());
                    myAsyncTask.setOnAsyncResponse(new MyAsyncTask.AsyncResponse() {
                        @Override
                        public void onSubjectsDataReceivedSuccess(List<Subjects> subjects) {
                            subjectsList = subjects;
                        }

                        @Override
                        public void onOtherDataReceivedSuccess(BaseClass baseClass) {

                        }

                        @Override
                        public void onDataReceivedFailed() {
                            ToastUtil.showToast(SearchActivity.this, "换个搜索词试试!");
                        }
                    });
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected Toolbar getToolBar() {
        return binding.toolbarSearch;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_search;
    }

    public List<Subjects> getData() {
        return subjectsList;
    }
}
