package com.spring_ballet.keep.fragment;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class BaseFragment<SV extends ViewDataBinding> extends Fragment {

    protected SV binding;
    protected Activity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResourceId(), container, false);
        binding = DataBindingUtil.inflate(getActivity().getLayoutInflater(), getLayoutResourceId(), container, false);
        initView();
        return view;
    }

    protected abstract void initView();

    protected abstract int getLayoutResourceId();
}
