package com.gelecegiyazanlar.ceptediyetisyen.module.dietitian;

import com.gelecegiyazanlar.ceptediyetisyen.base.mvp.view.BaseBindingView;
import com.gelecegiyazanlar.ceptediyetisyen.databinding.FragmentDietitianBinding;

final class DietitianContract {

    interface DietitianView extends BaseBindingView<FragmentDietitianBinding> {
        void listDietitian();
    }

}
