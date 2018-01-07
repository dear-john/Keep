package com.spring_ballet.keep.Adapter;

import android.content.Context;
import android.view.View;

import com.spring_ballet.keep.CommonUtils.GlideUtil;
import com.spring_ballet.keep.CommonUtils.IntentUtil;
import com.spring_ballet.keep.CommonUtils.StringListFormatUtil;
import com.spring_ballet.keep.DetailActivity;
import com.spring_ballet.keep.R;
import com.spring_ballet.keep.base.BaseAdapter;
import com.spring_ballet.keep.base.BaseViewHolder;
import com.spring_ballet.keep.bean.MovieBean.Subjects;
import com.spring_ballet.keep.databinding.MovieGeneralItemBinding;

import java.util.List;


public class MovieRecyclerViewAdapter extends BaseAdapter<MovieGeneralItemBinding, Subjects> {

    private final Context context;
    private final List<Subjects> subjectsList;

    public MovieRecyclerViewAdapter(Context context, List<Subjects> subjectsList) {
        this.context = context;
        this.subjectsList = subjectsList;
    }

    @Override
    protected Context getContext() {
        return context;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.movie_general_item;
    }

    @Override
    protected List<Subjects> getData() {
        return subjectsList;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        binding.setSubjects(subjectsList.get(position));
        final Subjects subjects = binding.getSubjects();
        GlideUtil.glideUtil(context, subjects.getImages().getMedium(), binding.ivMovieImage);
        String s = StringListFormatUtil.format(subjects.getGenres()) + "(" + subjects.getRating().getAverage() + "')";
        binding.tvMovieOtherInfo.setText(s);
        binding.layoutMovieItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.startIntent(context, DetailActivity.class, null, subjects);
            }
        });
        binding.executePendingBindings();
    }
}
