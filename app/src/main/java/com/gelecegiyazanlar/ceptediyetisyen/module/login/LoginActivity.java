package com.gelecegiyazanlar.ceptediyetisyen.module.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import com.gelecegiyazanlar.ceptediyetisyen.R;
import com.gelecegiyazanlar.ceptediyetisyen.base.BaseBindingActivity;
import com.gelecegiyazanlar.ceptediyetisyen.base.mvp.validator.FormValidator;
import com.gelecegiyazanlar.ceptediyetisyen.databinding.ActivityLoginBinding;
import com.gelecegiyazanlar.ceptediyetisyen.model.User;
import com.gelecegiyazanlar.ceptediyetisyen.module.MainActivity;
import com.gelecegiyazanlar.ceptediyetisyen.module.signup.SignupActivity;
import com.gelecegiyazanlar.ceptediyetisyen.utils.Constants;
import com.gelecegiyazanlar.ceptediyetisyen.utils.DialogUtils;
import com.gelecegiyazanlar.ceptediyetisyen.utils.Utils;

public class LoginActivity extends BaseBindingActivity<ActivityLoginBinding> implements LoginContract.LoginView, View.OnClickListener {


    private User user;
    private LoginContract.LoginPresetner<LoginContract.LoginView> loginPresetner;
    private ProgressDialog progressDialog;

    @Override
    public int attachView() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        initPresenter();

        getBinding().setListener(this);

        user = new User();
        getBinding().setUserModel(user);
    }

    @Override
    public void initPresenter() {


        loginPresetner = new LoginPresenterImpl();
        loginPresetner.attachView(this);
        loginPresetner.addValidator(new FormValidator() {

            @Override
            public boolean validate() {
                if (isEmptyField(user.getEmail(), getString(R.string.val_enter_username))) {
                    getBinding().edtMail.requestFocus();
                    return false;
                } else if (!isValidEmailId(user.getEmail(), getString(R.string.val_enter_username))) {
                    getBinding().edtMail.requestFocus();
                    return false;
                } else if (isEmptyField(getBinding().edtMail.getText().toString(), getString(R.string.val_enter_password))) {
                    getBinding().edtPassword.requestFocus();
                    return false;
                }
                return true;
            }

            @Override
            public void onValidationSuccess() {
                // if want to perform something on success
            }

            @Override
            public void onValidationError(String errorMessage) {
                DialogUtils.displayToast(LoginActivity.this, errorMessage);
            }

        });
    }

    @Override
    public void initToolbar() {

    }

    @Override
    public void onClick(View view) {
        if (view == getBinding().btnLogin) {
            Utils.hideSoftKeyBoard(this);
            loginPresetner.performLoginTask(user.getEmail(), getBinding().edtPassword.getText().toString());
        } else if (view == getBinding().btnSignUp) {
            goToSignup();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void goToSignup() {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    @Override
    public void goToMainPage(String username) {
        preferences.setBoolean(Constants.PREF_IS_LOGIN, true);

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(Constants.ARG_NAME, username);
        startActivity(intent);
        finish();
    }

    @Override
    public void showMessage(String errorMessage) {
        DialogUtils.displayDialog(LoginActivity.this, getString(R.string.alert), errorMessage);
    }

    @Override
    public void showProgress() {
        progressDialog = DialogUtils.showProgressDialog(this);
    }

    @Override
    public void hideProgress() {
        DialogUtils.hideProgressDialog(progressDialog);
    }

    @Override
    public void showNoData() {

    }

    @Override
    public void destroy() {
        loginPresetner.onDestroy();
    }
}
