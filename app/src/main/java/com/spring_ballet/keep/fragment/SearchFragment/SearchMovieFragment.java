package com.spring_ballet.keep.fragment.SearchFragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.spring_ballet.keep.Adapter.SearchMovieRecyclerViewAdapter;
import com.spring_ballet.keep.R;
import com.spring_ballet.keep.SearchActivity;
import com.spring_ballet.keep.base.BaseFragment;
import com.spring_ballet.keep.bean.MovieBean.Subjects;
import com.spring_ballet.keep.databinding.FragmentSearchMovieBinding;

import java.util.ArrayList;
import java.util.List;


public class SearchMovieFragment extends BaseFragment<FragmentSearchMovieBinding> {

    private SearchActivity activity;
    private boolean isFirst = true;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (SearchActivity) context;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_search_movie;
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
                binding.recyclerViewSearchMovie.setAdapter(new SearchMovieRecyclerViewAdapter(activity, subjectsList));
            }
        }
    }
}
