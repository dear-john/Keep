package com.spring_ballet.keep.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;

import com.spring_ballet.keep.CommonUtils.ApiList;
import com.spring_ballet.keep.CommonUtils.DiffTypeNumber;
import com.spring_ballet.keep.CommonUtils.GlideUtil;
import com.spring_ballet.keep.CommonUtils.IntentUtil;
import com.spring_ballet.keep.CommonUtils.MovieLoadDataUtil;
import com.spring_ballet.keep.R;
import com.spring_ballet.keep.TopMovieActivity;
import com.spring_ballet.keep.base.BaseFragment;
import com.spring_ballet.keep.bean.Event;
import com.spring_ballet.keep.databinding.FragmentMovieBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MovieFragment extends BaseFragment<FragmentMovieBinding> implements View.OnClickListener {

    private boolean isFirtVisible = true;
    private int count = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ObjectAnimator animator = ObjectAnimator.ofFloat(binding.ivLoading, "rotation", 0f, 360f);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(Animation.INFINITE);
        animator.setDuration(3000);
        animator.start();
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_movie;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Event event) {
        if (event.isLoadFinish()) {
            count++;
            if (count == 3) {
                binding.ivLoading.clearAnimation();
                binding.layoutMovieLoading.setVisibility(View.GONE);
                binding.nestedScrollViewMovie.setVisibility(View.VISIBLE);
                binding.layoutTopMovie.setOnClickListener(this);
                GlideUtil.glideUtil(activity, ApiList.TopMovieImageUrl, binding.ivTopMovie);
                binding.headHot.tvMovieList.setText(R.string.hot_movie_list);
                binding.headComing.tvMovieList.setText(R.string.movie_coming);
                binding.headUsbox.tvMovieList.setText(R.string.movie_usbox);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_top_movie:
                IntentUtil.startIntent(activity, TopMovieActivity.class);
                break;
            default:
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isFirtVisible) {
            isFirtVisible = false;
            MovieLoadDataUtil.loadData(ApiList.HotMovieList, DiffTypeNumber.HOTMOVIE, binding.recyclerViewMovieHot, activity);
            MovieLoadDataUtil.loadData(ApiList.MovieComingSoon, DiffTypeNumber.COMINGSOON, binding.recyclerViewMovieComing, activity);
            MovieLoadDataUtil.loadData(ApiList.UsBoxList, DiffTypeNumber.USBOX, binding.recyclerViewMovieUsbox, activity);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
