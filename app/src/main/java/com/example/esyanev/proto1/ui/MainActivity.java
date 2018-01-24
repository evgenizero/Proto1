package com.example.esyanev.proto1.ui;

import android.os.Bundle;

import com.example.esyanev.proto1.R;
import com.example.esyanev.proto1.databinding.ActivityMainBinding;
import com.example.esyanev.proto1.ui.main.ChooseFragment;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, ChooseFragment.newInstance())
                .commit();
    }
}
