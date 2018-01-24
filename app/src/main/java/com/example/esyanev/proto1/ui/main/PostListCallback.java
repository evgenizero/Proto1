package com.example.esyanev.proto1.ui.main;

import android.view.View;

import com.example.esyanev.proto1.data.local.entity.PostEntity;

/**
 * Created by esyanev on 22/01/18.
 */

public interface PostListCallback {
    void onPostClicked(PostEntity postEntity, View sharedView);
}
