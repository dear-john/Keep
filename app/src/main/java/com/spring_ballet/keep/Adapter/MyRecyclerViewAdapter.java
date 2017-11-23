package com.spring_ballet.keep.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.spring_ballet.keep.CommonUtils.IntentUtil;
import com.spring_ballet.keep.MovieDetailActivity;
import com.spring_ballet.keep.R;
import com.spring_ballet.keep.bean.MovieBean.Subjects;
import com.spring_ballet.keep.databinding.MovieGeneralItemBinding;

import java.util.List;


public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Subjects> subjectsList;

    public MyRecyclerViewAdapter(Context context, List<Subjects> subjects) {
        this.context = context;
        subjectsList = subjects;
    }

    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MovieGeneralItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.movie_general_item, parent, false);
        return new ViewHolder(itemBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder holder, int position) {
        MovieGeneralItemBinding itemBinding = DataBindingUtil.getBinding(holder.itemView);
        itemBinding.setSubjects(subjectsList.get(position));
        final Subjects subjects = itemBinding.getSubjects();
        Glide.with(context).load(subjects.images.medium).into(itemBinding.ivMovieImage);
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        for (String s : subjects.genres) {
            if (isFirst) {
                builder.append(s);
                isFirst = false;
            } else {
                builder.append("/").append(s);
            }
        }
        builder.append("(").append(subjects.rating.average).append("'").append(")");
        itemBinding.tvMovieOtherInfo.setText(builder.toString());
        itemBinding.layoutMovieItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.startIntent(context, MovieDetailActivity.class, subjects.id);
            }
        });
        itemBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return subjectsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
