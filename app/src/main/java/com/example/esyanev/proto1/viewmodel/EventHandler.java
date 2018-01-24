package com.example.esyanev.proto1.viewmodel;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;

import com.example.esyanev.proto1.R;
import com.example.esyanev.proto1.interactors.LoadPostsUseCase;
import com.example.esyanev.proto1.ui.main.ChooseFragment;
import com.example.esyanev.proto1.ui.main.PostListFragment;

/**
 * Created by esyanev on 23/01/18.
 */

public class EventHandler {

    private FragmentManager fragmentManager;

    public EventHandler(FragmentManager fragmentManager) {

        this.fragmentManager = fragmentManager;
    }

    public void onClickNetworkOnly(View v) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.container, PostListFragment.newInstance(LoadPostsUseCase.REQUEST_STRATEGY.NETWORK_ONLY))
                .addToBackStack("stack")
                .commit();
    }

    public void onClickNetworkOnlyCallback(View v) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.container, PostListFragment.newInstance(LoadPostsUseCase.REQUEST_STRATEGY.NETWORK_ONLY_CALLBACK))
                .addToBackStack("stack")
                .commit();
    }

    public void onClickNetworkDB(View v) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.container, PostListFragment.newInstance(LoadPostsUseCase.REQUEST_STRATEGY.NETWORK_DB))
                .addToBackStack("stack")
                .commit();
    }

    public void onClickNetworkEncryptedDB(View v) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.container, PostListFragment.newInstance(LoadPostsUseCase.REQUEST_STRATEGY.NETWORK_ENCRYPTED_DB))
                .addToBackStack("stack")
                .commit();
    }
}
