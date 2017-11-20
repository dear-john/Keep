package com.spring_ballet.keep;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.spring_ballet.keep.Adapter.MyFragmentPagerAdapter;
import com.spring_ballet.keep.databinding.ActivityMainBinding;
import com.spring_ballet.keep.fragment.BookFragment;
import com.spring_ballet.keep.fragment.GankFragment;
import com.spring_ballet.keep.fragment.MovieFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new GankFragment());
        fragmentList.add(new MovieFragment());
        fragmentList.add(new BookFragment());
        binding.viewPagerMainActivity.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));
        binding.viewPagerMainActivity.setOffscreenPageLimit(2);
        binding.viewPagerMainActivity.addOnPageChangeListener(this);
        binding.viewPagerMainActivity.setCurrentItem(0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setSelectedPage(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_menu:
                break;
            case R.id.iv_gank:
                setSelectedPage(0);
                break;
            case R.id.iv_movie:
                setSelectedPage(1);
                break;
            case R.id.iv_douban:
                setSelectedPage(2);
                break;
            case R.id.layout_search:
                break;
            default:
        }
    }

    private void setSelectedPage(int position) {
        switch (position) {
            case 0:
                binding.ivGank.setSelected(true);
                binding.ivMovie.setSelected(false);
                binding.ivDouban.setSelected(false);
                break;
            case 1:
                binding.ivGank.setSelected(false);
                binding.ivMovie.setSelected(true);
                binding.ivDouban.setSelected(false);
                break;
            case 2:
                binding.ivGank.setSelected(false);
                binding.ivMovie.setSelected(false);
                binding.ivDouban.setSelected(true);
                break;
            default:
        }
    }
}
