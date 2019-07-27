package com.gelecegiyazanlar.ceptediyetisyen.module.login;

import com.gelecegiyazanlar.ceptediyetisyen.base.mvp.callback.ICallbackListener;
import com.gelecegiyazanlar.ceptediyetisyen.base.mvp.validator.IFormValidator;
import com.gelecegiyazanlar.ceptediyetisyen.impl.StartInteracter;

public class LoginPresenterImpl implements LoginContract.LoginPresetner<LoginContract.LoginView>, ICallbackListener<String> {

    private IFormValidator formValidator;
    private LoginContract.LoginView view;
    private StartInteracter loginInteracter;

    LoginPresenterImpl() {
        loginInteracter = new StartInteracter();
    }

    @Override
    public void addValidator(IFormValidator formValidator) {
        this.formValidator = formValidator;
    }

    @Override
    public void attachView(LoginContract.LoginView view) {
        this.view = view;
    }

    @Override
    public void performLoginTask(String username, String password) {
        if (formValidator != null) {
            if (formValidator.validate()) {
                view.showProgress();
                loginInteracter.doLogin(username, password, this);
            }
        }
    }



    @Override
    public void onSuccess(String data) {
        view.hideProgress();
        formValidator.onValidationError("You're logged in");
        view.goToMainPage("");
    }

    @Override
    public void onFailure(Throwable t) {
        view.hideProgress();
        formValidator.onValidationError("Login Error");
    }

    @Override
    public void onDestroy() {

    }
}
