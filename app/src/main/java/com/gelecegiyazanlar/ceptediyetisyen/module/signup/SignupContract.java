package com.gelecegiyazanlar.ceptediyetisyen.module.signup;


import com.gelecegiyazanlar.ceptediyetisyen.base.mvp.presenter.BaseFormPresenter;
import com.gelecegiyazanlar.ceptediyetisyen.base.mvp.view.BaseBindingView;
import com.gelecegiyazanlar.ceptediyetisyen.base.mvp.view.BaseView;
import com.gelecegiyazanlar.ceptediyetisyen.model.User;
import com.gelecegiyazanlar.ceptediyetisyen.databinding.ActivitySignupBinding;

final class SignupContract {

    interface SignupView extends BaseBindingView<ActivitySignupBinding> {
        void goToMainPage(String username);
    }

    interface SignupPresetner<T extends BaseView> extends BaseFormPresenter<T> {
        void performSignupTask(User user, String password);
    }

}
