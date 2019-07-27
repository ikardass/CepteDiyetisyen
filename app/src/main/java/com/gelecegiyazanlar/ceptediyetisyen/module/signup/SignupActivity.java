package com.gelecegiyazanlar.ceptediyetisyen.module.signup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import com.gelecegiyazanlar.ceptediyetisyen.model.User;
import com.gelecegiyazanlar.ceptediyetisyen.databinding.ActivitySignupBinding;
import com.gelecegiyazanlar.ceptediyetisyen.R;
import com.gelecegiyazanlar.ceptediyetisyen.base.BaseBindingActivity;
import com.gelecegiyazanlar.ceptediyetisyen.base.mvp.validator.FormValidator;
import com.gelecegiyazanlar.ceptediyetisyen.module.MainActivity;
import com.gelecegiyazanlar.ceptediyetisyen.utils.Constants;
import com.gelecegiyazanlar.ceptediyetisyen.utils.DialogUtils;
import com.gelecegiyazanlar.ceptediyetisyen.utils.Utils;

public class SignupActivity extends BaseBindingActivity<ActivitySignupBinding> implements SignupContract.SignupView, View.OnClickListener {

    private User user;
    private SignupContract.SignupPresetner<SignupContract.SignupView> signupPresetner;

    private ProgressDialog progressDialog;

    @Override
    public int attachView() {
        return R.layout.activity_signup;
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
        signupPresetner = new SignupPresenterImpl();
        signupPresetner.attachView(this);
        signupPresetner.addValidator(new FormValidator() {

            @Override
            public boolean validate() {

                if (isEmptyField(user.getName(), getString(R.string.val_enter_name))) {
                    getBinding().edtName.requestFocus();
                    return false;
                }
                if (isEmptyField(user.getSurname(), getString(R.string.val_enter_surname))) {
                    getBinding().edtSurname.requestFocus();
                    return false;
                }
                if (!isValidEmailId(user.getEmail(), getString(R.string.val_enter_username))) {
                    getBinding().edtMail.requestFocus();
                    return false;
                }
                if (isEmptyField(getBinding().edtPassword.getText().toString(), getString(R.string.val_enter_password))) {
                    getBinding().edtPassword.requestFocus();
                    return false;
                }
                if (isEmptyField(getBinding().edtConfirmPassword.getText().toString(), getString(R.string.val_confirm_password))) {
                    getBinding().edtConfirmPassword.requestFocus();
                    return false;
                }
                if (!isConfirmPasswordSame(getBinding().edtPassword.getText().toString(), getBinding().edtConfirmPassword.getText().toString(),
                        getString(R.string.val_confirm_password))) {
                    getBinding().edtConfirmPassword.requestFocus();
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
                DialogUtils.displayToast(SignupActivity.this, errorMessage);
            }

        });
    }

    @Override
    public void initToolbar() {

    }

    @Override
    public void onClick(View view) {
        if (view == getBinding().btnSignup) {
            Utils.hideSoftKeyBoard(this);
            signupPresetner.performSignupTask(user, getBinding().edtPassword.getText().toString());
        }
    }

    @Override
    public void goToMainPage(String username) {
        preferences.setBoolean(Constants.PREF_IS_LOGIN, true);
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void showMessage(String errorMessage) {
        DialogUtils.displayDialog(SignupActivity.this, getString(R.string.alert), errorMessage);
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
        signupPresetner.onDestroy();
    }
}
