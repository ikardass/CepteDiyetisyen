package com.gelecegiyazanlar.ceptediyetisyen.module.healtyRecipe;

import com.gelecegiyazanlar.ceptediyetisyen.base.mvp.view.BaseBindingView;
import com.gelecegiyazanlar.ceptediyetisyen.databinding.FragmentHealtyRecipeBinding;

public final class HealtyRecipeContract {

    interface HealtyRecipeViev extends BaseBindingView<FragmentHealtyRecipeBinding> {
        void fetchHealtyRecipe();
    }

}
