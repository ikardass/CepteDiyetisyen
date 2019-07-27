package com.gelecegiyazanlar.ceptediyetisyen.module.dietitian;

import android.view.View;

import com.gelecegiyazanlar.ceptediyetisyen.R;
import com.gelecegiyazanlar.ceptediyetisyen.base.BaseBindingFragment;
import com.gelecegiyazanlar.ceptediyetisyen.databinding.FragmentDietitianBinding;
import com.gelecegiyazanlar.ceptediyetisyen.utils.DialogUtils;


public class DietitianFragment extends BaseBindingFragment<FragmentDietitianBinding> implements DietitianContract.DietitianView {

    @Override
    protected int attachView() {
        return R.layout.fragment_dietitian;
    }

    @Override
    protected void initView(View view) {
        initPresenter();
    }

    @Override
    protected void initToolbar() {

    }


    @Override
    public void initPresenter() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showNoData() {

    }

    @Override
    public void showMessage(String errorMessage) {
        DialogUtils.displayToast(getActivity(), errorMessage);
    }

    @Override
    public void destroy() {

    }


    @Override
    public void listDietitian() {

    }
}
