package com.gelecegiyazanlar.ceptediyetisyen.base.mvp.presenter;

import com.gelecegiyazanlar.ceptediyetisyen.base.mvp.view.BaseView;

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void onDestroy();
}