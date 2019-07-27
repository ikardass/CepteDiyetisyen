package com.gelecegiyazanlar.ceptediyetisyen.base.mvp.presenter;

import com.gelecegiyazanlar.ceptediyetisyen.base.mvp.validator.IFormValidator;
import com.gelecegiyazanlar.ceptediyetisyen.base.mvp.view.BaseView;

public interface BaseFormPresenter<T extends BaseView> extends BasePresenter<T> {
    void addValidator(IFormValidator formValidator);
}