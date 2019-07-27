package com.gelecegiyazanlar.ceptediyetisyen.module.signup;


import com.gelecegiyazanlar.ceptediyetisyen.base.mvp.callback.ICallbackListener;
import com.gelecegiyazanlar.ceptediyetisyen.base.mvp.validator.IFormValidator;
import com.gelecegiyazanlar.ceptediyetisyen.model.User;
import com.gelecegiyazanlar.ceptediyetisyen.impl.StartInteracter;

public class SignupPresenterImpl implements SignupContract.SignupPresetner<SignupContract.SignupView>, ICallbackListener<String> {

    private IFormValidator formValidator;
    private SignupContract.SignupView view;
    private StartInteracter loginInteracter;

    SignupPresenterImpl() {
        loginInteracter = new StartInteracter();
    }

    @Override
    public void addValidator(IFormValidator formValidator) {
        this.formValidator = formValidator;
    }

    @Override
    public void attachView(SignupContract.SignupView view) {
        this.view = view;
    }

    @Override
    public void performSignupTask(User user, String password) {
        if (formValidator != null) {
            if (formValidator.validate()) {
                view.showProgress();
                loginInteracter.doSignup(user, password,this);
            }
        }
    }

    @Override
    public void onSuccess(String data) {
        view.hideProgress();
        view.goToMainPage("");
    }

    @Override
    public void onFailure(Throwable t) {
        view.hideProgress();
        formValidator.onValidationError(t.getMessage());
    }

    @Override
    public void onDestroy() {

    }
}
