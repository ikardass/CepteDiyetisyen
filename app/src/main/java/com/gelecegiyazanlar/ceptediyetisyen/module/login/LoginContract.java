package com.gelecegiyazanlar.ceptediyetisyen.module.login;

import com.gelecegiyazanlar.ceptediyetisyen.base.mvp.presenter.BaseFormPresenter;
import com.gelecegiyazanlar.ceptediyetisyen.base.mvp.view.BaseBindingView;
import com.gelecegiyazanlar.ceptediyetisyen.base.mvp.view.BaseView;
import com.gelecegiyazanlar.ceptediyetisyen.databinding.ActivityLoginBinding;


final class LoginContract {

    interface LoginView extends BaseBindingView<ActivityLoginBinding> {
        void goToSignup();
        void goToMainPage(String username);
    }

    interface LoginPresetner<T extends BaseView> extends BaseFormPresenter<T> {
        void performLoginTask(String username, String password);
    }
}
