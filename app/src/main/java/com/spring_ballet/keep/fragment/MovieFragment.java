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
            loadData(ApiList.HotMovieList, MovieListType.HOTMOVIE);
            loadData(ApiList.MovieComingSoon, MovieListType.COMINGSOON);
//            loadData(ApiList.KouBeiList, MovieListType.KOUBEI);
            loadData(ApiList.UsBoxList, MovieListType.USBOX);
//            loadData(ApiList.NewMovieList, MovieListType.NEWMOVIE);
        }
    }

    private void loadData(final String url, final int type) {
        MyAsyncTask myAsyncTask = new MyAsyncTask(type);
        myAsyncTask.execute(url);
        myAsyncTask.setOnAsyncResponse(new MyAsyncTask.AsyncResponse() {
            @Override
            public void onSubjectsDataReceivedSuccess(List<Subjects> subjects) {
                if (subjects != null) {
                    LinearLayoutManager manager = new LinearLayoutManager(getActivity(),
                            LinearLayoutManager.HORIZONTAL, false);
                    switch (type) {
                        case MovieListType.HOTMOVIE:
                            binding.recyclerViewMovieHot.setLayoutManager(manager);
                            binding.recyclerViewMovieHot.setAdapter(new MyRecyclerViewAdapter(getActivity(), subjects));
                            break;
                        case MovieListType.COMINGSOON:
                            binding.recyclerViewMovieComing.setLayoutManager(manager);
                            binding.recyclerViewMovieComing.setAdapter(new MyRecyclerViewAdapter(getActivity(), subjects));
                            break;
                        case MovieListType.KOUBEI:
                            break;
                        case MovieListType.USBOX:
                            binding.recyclerViewMovieUsbox.setLayoutManager(manager);
                            binding.recyclerViewMovieUsbox.setAdapter(new MyRecyclerViewAdapter(getActivity(), subjects));
                            break;
                        case MovieListType.NEWMOVIE:
                            break;
                        default:
                    }
                }
            }

            @Override
            public void onMovieDataReceivedSuccess(Movie movie) {

            }

            @Override
            public void onDataReceivedFailed() {
                ToastUtil.showToast(getActivity(), "加载出错了");
            }
        });
    }
}
