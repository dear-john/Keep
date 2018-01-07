package com.spring_ballet.keep.Adapter;

import android.content.Context;
import android.view.View;

import com.spring_ballet.keep.CommonUtils.GlideUtil;
import com.spring_ballet.keep.CommonUtils.IntentUtil;
import com.spring_ballet.keep.DetailActivity;
import com.spring_ballet.keep.R;
import com.spring_ballet.keep.base.BaseAdapter;
import com.spring_ballet.keep.base.BaseViewHolder;
import com.spring_ballet.keep.bean.BookBean.Books;
import com.spring_ballet.keep.databinding.ItemGeneralInfoBinding;

import java.util.List;


public class BookRecyclerViewAdapter extends BaseAdapter<ItemGeneralInfoBinding, Books> {

    private final Context context;
    private final List<Books> booksList;

    public BookRecyclerViewAdapter(Context context, List<Books> booksList) {
        this.context = context;
        this.booksList = booksList;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.item_general_info;
    }

    @Override
    protected List<Books> getData() {
        return booksList;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        final Books books = booksList.get(position);
        GlideUtil.glideUtil(context, books.getImages().getLarge(), binding.ivPosterImage);
        binding.tvTitle.setText(books.getTitle());
        final String data = books.getRating().getAverage() + "分";
        binding.tvMoreInfo.setText(data);
        binding.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.startIntent(context, DetailActivity.class, books, null);
            }
        });
        binding.executePendingBindings();
    }

    @Override
    protected Context getContext() {
        return context;
    }
}
