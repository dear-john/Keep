package com.spring_ballet.keep.Adapter;

import android.content.Context;
import android.view.View;

import com.spring_ballet.keep.CommonUtils.GlideUtil;
import com.spring_ballet.keep.CommonUtils.IntentUtil;
import com.spring_ballet.keep.CommonUtils.MovieUtils.CastsFormatUtil;
import com.spring_ballet.keep.CommonUtils.MovieUtils.DirectorsFormatUtil;
import com.spring_ballet.keep.CommonUtils.StringListFormatUtil;
import com.spring_ballet.keep.DetailActivity;
import com.spring_ballet.keep.R;
import com.spring_ballet.keep.base.BaseAdapter;
import com.spring_ballet.keep.base.BaseViewHolder;
import com.spring_ballet.keep.bean.MovieBean.Subjects;
import com.spring_ballet.keep.databinding.MovieDetailItemBinding;

import java.util.List;


public class SearchMovieRecyclerViewAdapter extends BaseAdapter<MovieDetailItemBinding, Subjects> {

    private final Context context;
    private final List<Subjects> subjectsList;

    public SearchMovieRecyclerViewAdapter(Context context, List<Subjects> subjects) {
        this.context = context;
        subjectsList = subjects;
    }

    @Override
    protected Context getContext() {
        return context;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.movie_detail_item;
    }

    @Override
    protected List<Subjects> getData() {
        return subjectsList;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        binding.setSubjects(subjectsList.get(position));
        final Subjects subjects2 = binding.getSubjects();
        GlideUtil.glideUtil(context, subjects2.getImages().getMedium(), binding.ivMovieItemPoster);
        binding.tvMovieItemDir.setText(DirectorsFormatUtil.format(subjects2.getDirectors()));
        binding.tvMovieItemCasts.setText(CastsFormatUtil.format(subjects2.getCasts()));
        binding.tvMovieItemType.setText(StringListFormatUtil.format(subjects2.getGenres()));
        binding.layoutMovieDetailItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.startIntent(context, DetailActivity.class, null, subjects2);
            }
        });
        binding.executePendingBindings();
    }
}
