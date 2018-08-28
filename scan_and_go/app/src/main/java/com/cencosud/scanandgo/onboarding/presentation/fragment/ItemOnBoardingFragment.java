package com.cencosud.scanandgo.onboarding.presentation.fragment;

import android.os.Bundle;
import android.view.View;

import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.FragmentOnboardingBinding;
import com.cencosud.scanandgo.databinding.ItemFragmentOnboardingBinding;
import com.core.presentation.fragment.BaseFragment;

/**
 * Created by joseamaro on 23-04-18.
 */

public class ItemOnBoardingFragment extends BaseFragment<ItemFragmentOnboardingBinding> {

    public static ItemOnBoardingFragment newInstance(int image, int texto, int icon) {

        ItemOnBoardingFragment f = new ItemOnBoardingFragment();
        Bundle b = new Bundle();
        b.putInt("image", image);
        b.putInt("text", texto);
        b.putInt("icon", icon);
        f.setArguments(b);
        return f;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_fragment_onboarding;
    }

    @Override
    protected void initView() {

        int image = getArguments().getInt("image");
        int text = getArguments().getInt("text");
        int icon = getArguments().getInt("icon");
        binder.rlBackgroundCard.setBackground(getResources().getDrawable(image));
        binder.tvText.setText(getResources().getString(text));
        if(icon != 0){
            binder.givCard.setVisibility(View.GONE);
            binder.ivCard.setVisibility(View.VISIBLE);
            binder.ivCard.setBackground(getResources().getDrawable(icon));
        }

    }

    @Override
    protected void injectDependencies() {

    }
}
