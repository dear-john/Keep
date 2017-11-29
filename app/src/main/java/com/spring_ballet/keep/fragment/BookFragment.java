package com.spring_ballet.keep.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spring_ballet.keep.Adapter.MyFragmentPagerAdapter;
import com.spring_ballet.keep.CommonUtils.ApiList;
import com.spring_ballet.keep.R;
import com.spring_ballet.keep.databinding.FragmentBookBinding;
import com.spring_ballet.keep.fragment.Book.ShengHuoFragment;
import com.spring_ballet.keep.fragment.Book.WenHuaFragment;
import com.spring_ballet.keep.fragment.Book.WenXueFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookFragment extends Fragment {

    private boolean isFirst = true;
    private FragmentBookBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_book, container, false);
        return binding.getRoot();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isFirst) {
            isFirst = false;
            //注意title和fragment的对应顺序
            List<Fragment> fragmentList = new ArrayList<>();
            fragmentList.add(WenXueFragment.newInstance(getActivity()));
            fragmentList.add(WenHuaFragment.newInstance(getActivity()));
            fragmentList.add(ShengHuoFragment.newInstance(getActivity()));
            List<String> titleList = new ArrayList<>();
            titleList.addAll(Arrays.asList(ApiList.BookSearchTags));
            binding.bookTabViewpager.viewPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(), fragmentList, titleList));
            binding.bookTabViewpager.viewPager.setOffscreenPageLimit(2);
            binding.bookTabViewpager.tabLayout.setupWithViewPager(binding.bookTabViewpager.viewPager);
        }
    }
}
