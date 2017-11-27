package com.spring_ballet.keep.fragment.SearchFragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spring_ballet.keep.Adapter.MyRecyclerViewAdapter;
import com.spring_ballet.keep.CommonUtils.CommonField;
import com.spring_ballet.keep.R;
import com.spring_ballet.keep.SearchActivity;
import com.spring_ballet.keep.bean.MovieBean.Subjects;
import com.spring_ballet.keep.databinding.FragmentSearchMovieBinding;

import java.util.ArrayList;
import java.util.List;


public class SearchMovieFragment extends Fragment {

    private FragmentSearchMovieBinding binding;
    private SearchActivity activity;
    private boolean isFirst = true;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (SearchActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_movie, container, false);
        return binding.getRoot();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            List<Subjects> subjectsList = new ArrayList<>();
            if (isFirst) {
                subjectsList = activity.getData();
                isFirst = false;
            }
            if (subjectsList != null) {
                binding.recyclerViewSearchMovie.setLayoutManager(new LinearLayoutManager(activity));
                binding.recyclerViewSearchMovie.setAdapter(new MyRecyclerViewAdapter(activity, subjectsList, CommonField.MOVIE_SEARCH_ITEM));
            }
        }
    }
}
