package com.gelecegiyazanlar.ceptediyetisyen.base.mvp.view;


import androidx.databinding.ViewDataBinding;

public interface BaseBindingView<T extends ViewDataBinding> extends BaseView {
    T getBinding();
}
