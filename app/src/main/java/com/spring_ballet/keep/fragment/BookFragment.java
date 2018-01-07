package com.spring_ballet.keep.fragment;

import android.support.v4.app.Fragment;

import com.spring_ballet.keep.Adapter.MyFragmentPagerAdapter;
import com.spring_ballet.keep.CommonUtils.ApiList;
import com.spring_ballet.keep.R;
import com.spring_ballet.keep.base.BaseFragment;
import com.spring_ballet.keep.databinding.FragmentBookBinding;
import com.spring_ballet.keep.fragment.Book.ShengHuoFragment;
import com.spring_ballet.keep.fragment.Book.WenHuaFragment;
import com.spring_ballet.keep.fragment.Book.WenXueFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookFragment extends BaseFragment<FragmentBookBinding> {

    private boolean isFirst = true;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_book;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isFirst) {
            isFirst = false;
            //注意title和fragment的对应顺序
            List<Fragment> fragmentList = new ArrayList<>();
            fragmentList.add(new WenXueFragment());
            fragmentList.add(new WenHuaFragment());
            fragmentList.add(new ShengHuoFragment());
            List<String> titleList = new ArrayList<>();
            titleList.addAll(Arrays.asList(ApiList.BookSearchTags));
            binding.bookTabViewpager.viewPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(), fragmentList, titleList));
            binding.bookTabViewpager.viewPager.setOffscreenPageLimit(2);
            binding.bookTabViewpager.tabLayout.setupWithViewPager(binding.bookTabViewpager.viewPager);
        }
    }
}
