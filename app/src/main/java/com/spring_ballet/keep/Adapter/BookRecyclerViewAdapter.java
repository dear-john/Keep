package com.spring_ballet.keep.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.spring_ballet.keep.CommonUtils.IntentUtil;
import com.spring_ballet.keep.CommonUtils.StringListFormatUtil;
import com.spring_ballet.keep.CommonUtils.TagFormatUtil;
import com.spring_ballet.keep.DetailActivity;
import com.spring_ballet.keep.R;
import com.spring_ballet.keep.bean.BookBean.Books;
import com.spring_ballet.keep.databinding.ItemGeneralInfoBinding;

import java.util.List;


public class BookRecyclerViewAdapter extends RecyclerView.Adapter<BookRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Books> booksList;
    private ItemGeneralInfoBinding binding;

    public BookRecyclerViewAdapter(Context context, List<Books> books) {
        this.context = context;
        booksList = books;
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.item_general_info, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Books books = booksList.get(position);
        Glide.with(context)
                .setDefaultRequestOptions(new RequestOptions()
                        .placeholder(R.drawable.load_err)
                        .error(R.drawable.load_err))
                .load(books.images.large)
                .into(binding.ivPosterImage);
        binding.tvTitle.setText(books.title);
        final String data = books.rating.average + "分";
        binding.tvMoreInfo.setText(data);
        binding.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.startIntent(context, DetailActivity.class, "BOOK", books.title,
                        StringListFormatUtil.format(books.author), String.valueOf(books.rating.average),
                        String.valueOf(books.rating.numRaters), books.pubdate, books.publisher,
                        books.summary, books.author_intro, books.alt, books.images.large,
                        String.valueOf(books.pages), TagFormatUtil.format(books.tags));
            }
        });
        binding.executePendingBindings();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
