package com.spring_ballet.keep.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.spring_ballet.keep.Adapter.MyRecyclerViewAdapter;
import com.spring_ballet.keep.CommonUtils.ApiList;
import com.spring_ballet.keep.CommonUtils.IntentUtil;
import com.spring_ballet.keep.CommonUtils.LoadDataUtil;
import com.spring_ballet.keep.CommonUtils.MovieListType;
import com.spring_ballet.keep.CommonUtils.MyAsyncTask;
import com.spring_ballet.keep.CommonUtils.ToastUtil;
import com.spring_ballet.keep.R;
import com.spring_ballet.keep.TopMovieActivity;
import com.spring_ballet.keep.bean.Movie;
import com.spring_ballet.keep.bean.MovieBean.Subjects;
import com.spring_ballet.keep.databinding.FragmentMovieBinding;

import java.util.List;

public class MovieFragment extends Fragment implements View.OnClickListener {

    private boolean isFirtVisible = true;
    private FragmentMovieBinding binding;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false);
        binding.layoutTopMovie.setOnClickListener(this);
        Glide.with(getActivity()).load(ApiList.TopMovieImageUrl).into(binding.ivTopMovie);
        binding.headHot.tvMovieList.setText(R.string.hot_movie_list);
        binding.headComing.tvMovieList.setText(R.string.movie_coming);
        binding.headUsbox.tvMovieList.setText(R.string.movie_usbox);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_top_movie:
                IntentUtil.startIntent(getActivity(), TopMovieActivity.class);
                break;
            default:
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isFirtVisible) {
            isFirtVisible = false;
            LoadDataUtil.loadData(ApiList.HotMovieList, MovieListType.HOTMOVIE, binding.recyclerViewMovieHot, getActivity());
            LoadDataUtil.loadData(ApiList.MovieComingSoon, MovieListType.COMINGSOON, binding.recyclerViewMovieComing, getActivity());
            LoadDataUtil.loadData(ApiList.UsBoxList, MovieListType.USBOX, binding.recyclerViewMovieUsbox, getActivity());
//            loadData(ApiList.KouBeiList, MovieListType.KOUBEI);
//            loadData(ApiList.NewMovieList, MovieListType.NEWMOVIE);
        }
    }
}
