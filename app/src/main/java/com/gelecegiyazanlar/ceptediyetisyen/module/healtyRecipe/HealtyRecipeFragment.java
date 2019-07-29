package com.gelecegiyazanlar.ceptediyetisyen.module.healtyRecipe;

import android.os.Handler;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.gelecegiyazanlar.ceptediyetisyen.R;
import com.gelecegiyazanlar.ceptediyetisyen.base.BaseBindingFragment;
import com.gelecegiyazanlar.ceptediyetisyen.databinding.FragmentHealtyRecipeBinding;
import com.gelecegiyazanlar.ceptediyetisyen.impl.HealtyRecipeInteractor;
import com.gelecegiyazanlar.ceptediyetisyen.utils.DialogUtils;

public class HealtyRecipeFragment extends BaseBindingFragment<FragmentHealtyRecipeBinding> implements HealtyRecipeContract.HealtyRecipeViev {

    private HealtyRecipeAdapter adapter;
    private HealtyRecipeInteractor interacter;

    @Override
    protected int attachView() {
        return R.layout.fragment_healty_recipe;
    }

    @Override
    protected void initView(View view) {
        initRecyclerView();
        initPresenter();
        fetchHealtyRecipe();
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    public void fetchHealtyRecipe() {
        showProgress();
        interacter.findList();
        new Handler().postDelayed(this::hideProgress, 2000);
    }

    @Override
    public void initPresenter() {
        interacter = new HealtyRecipeInteractor(adapter.getiFirebaseCallbackListener());
    }

    @Override
    public void destroy() {

    }

    @Override
    public void showProgress() {
        if (!getBinding().swipeRefreshLayout.isRefreshing()) {
            getBinding().frBrowseRecyclerView.setVisibility(View.GONE);
            getBinding().frBrowseLoadingProgress.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        getBinding().frBrowseLoadingProgress.setVisibility(View.GONE);
        getBinding().frBrowseRecyclerView.setVisibility(View.VISIBLE);
        if (getBinding().swipeRefreshLayout.isRefreshing())
            getBinding().swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showNoData() {

    }

    @Override
    public void showMessage(String errorMessage) {
        DialogUtils.displayToast(getActivity(), errorMessage);
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        getBinding().frBrowseRecyclerView.setLayoutManager(layoutManager);
        adapter = new HealtyRecipeAdapter(this);
        getBinding().frBrowseRecyclerView.setAdapter(adapter);
    }

}
