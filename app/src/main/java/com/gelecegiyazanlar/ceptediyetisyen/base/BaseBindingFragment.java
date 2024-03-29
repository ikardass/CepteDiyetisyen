package com.gelecegiyazanlar.ceptediyetisyen.base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.gelecegiyazanlar.ceptediyetisyen.utils.PreferenceUtils;


public abstract class BaseBindingFragment<T extends ViewDataBinding> extends Fragment {

    public BaseBindingActivity context;
    T binding;

    protected abstract int attachView();

    protected abstract void initView(View view);

    protected abstract void initToolbar();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = (BaseBindingActivity) getActivity();
    }

    public T getBinding() {
        return binding;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, attachView(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initView(view);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden)
            initToolbar();
    }

    protected PreferenceUtils getPreferences() {
        return context.preferences;
    }
}
