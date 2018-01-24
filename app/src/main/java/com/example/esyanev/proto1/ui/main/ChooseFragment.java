package com.example.esyanev.proto1.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.esyanev.proto1.R;
import com.example.esyanev.proto1.databinding.ChooseFragmentBinding;
import com.example.esyanev.proto1.ui.BaseFragment;
import com.example.esyanev.proto1.viewmodel.ChooseViewModel;
import com.example.esyanev.proto1.viewmodel.EventHandler;

/**
 * Created by esyanev on 23/01/18.
 */

public class ChooseFragment extends BaseFragment<ChooseViewModel, ChooseFragmentBinding> {

    public static ChooseFragment newInstance() {
        return new ChooseFragment();
    }

    @Override
    public Class<ChooseViewModel> getViewModel() {
        return ChooseViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.choose_fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        dataBinding.setHandler(new EventHandler(getFragmentManager()));
        return dataBinding.getRoot();
    }
}
