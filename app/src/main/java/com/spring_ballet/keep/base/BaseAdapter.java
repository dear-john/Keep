package com.spring_ballet.keep.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;


public abstract class BaseAdapter<T extends ViewDataBinding, S extends BaseClass>
        extends RecyclerView.Adapter<BaseViewHolder> {

    protected T binding;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), getLayoutResourceId(), parent, false);
        return new BaseViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

    }

    protected abstract Context getContext();

    protected abstract int getLayoutResourceId();

    protected abstract List<S> getData();

    @Override
    public int getItemCount() {
        return getData().size();
    }
}
