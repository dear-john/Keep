package com.spring_ballet.keep.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.spring_ballet.keep.CommonUtils.CommonField;
import com.spring_ballet.keep.CommonUtils.IntentUtil;
import com.spring_ballet.keep.CommonUtils.MovieUtils.CastsFormatUtil;
import com.spring_ballet.keep.CommonUtils.MovieUtils.DirectorsFormatUtil;
import com.spring_ballet.keep.CommonUtils.StringListFormatUtil;
import com.spring_ballet.keep.DetailActivity;
import com.spring_ballet.keep.R;
import com.spring_ballet.keep.bean.MovieBean.Subjects;
import com.spring_ballet.keep.databinding.MovieDetailItemBinding;
import com.spring_ballet.keep.databinding.MovieGeneralItemBinding;

import java.util.List;


public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Subjects> subjectsList;
    private int resId;
    private MovieGeneralItemBinding generalItemBinding;
    private MovieDetailItemBinding detailItemBinding;

    public MyRecyclerViewAdapter(Context context, List<Subjects> subjects, int resId) {
        this.context = context;
        subjectsList = subjects;
        this.resId = resId;
    }

    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        generalItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.movie_general_item, parent, false);
        detailItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.movie_detail_item, parent, false);
        switch (resId) {
            case CommonField.MOVIE_GENERAL_ITEM:
                return new ViewHolder(generalItemBinding.getRoot());
            case CommonField.MOVIE_SEARCH_ITEM:
                return new ViewHolder(detailItemBinding.getRoot());
            default:
                return new ViewHolder(generalItemBinding.getRoot());
        }
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder holder, int position) {
        switch (resId) {
            case CommonField.MOVIE_GENERAL_ITEM:
                this.generalItemBinding.setSubjects(subjectsList.get(position));
                final Subjects subjects = this.generalItemBinding.getSubjects();
                Glide.with(context)
                        .setDefaultRequestOptions(new RequestOptions()
                                .placeholder(R.drawable.load_err)
                                .error(R.drawable.load_err))
                        .load(subjects.images.medium)
                        .into(generalItemBinding.ivMovieImage);
                String s = StringListFormatUtil.format(subjects.genres) + "(" + subjects.rating.average + "')";
                generalItemBinding.tvMovieOtherInfo.setText(s);
                generalItemBinding.layoutMovieItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        IntentUtil.startIntent(context, DetailActivity.class, "MOVIE",
                                subjects.title, DirectorsFormatUtil.format(subjects.directors),
                                CastsFormatUtil.format(subjects.casts), StringListFormatUtil.format(subjects.genres),
                                String.valueOf(subjects.id), subjects.images.large);
                    }
                });
                generalItemBinding.executePendingBindings();
                break;
            case CommonField.MOVIE_SEARCH_ITEM:
                this.detailItemBinding.setSubjects(subjectsList.get(position));
                final Subjects subjects2 = this.detailItemBinding.getSubjects();
                Glide.with(context)
                        .setDefaultRequestOptions(new RequestOptions()
                                .placeholder(R.drawable.load_err)
                                .error(R.drawable.load_err)
                                .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                        .load(subjects2.images.medium)
                        .into(detailItemBinding.ivMovieItemPoster);
                final String dir = DirectorsFormatUtil.format(subjects2.directors);
                detailItemBinding.tvMovieItemDir.setText(dir);
                final String cast = CastsFormatUtil.format(subjects2.casts);
                detailItemBinding.tvMovieItemCasts.setText(cast);
                final String type = StringListFormatUtil.format(subjects2.genres);
                detailItemBinding.tvMovieItemType.setText(type);
                detailItemBinding.layoutMovieDetailItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        IntentUtil.startIntent(context, DetailActivity.class, "MOVIE",
                                subjects2.title, dir, cast, type, String.valueOf(subjects2.id), subjects2.images.large);
                    }
                });
                detailItemBinding.executePendingBindings();
                break;
            default:
        }
    }

    @Override
    public int getItemCount() {
        return subjectsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
