package com.example.esyanev.proto1.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.LiveDataReactiveStreams;
import android.arch.lifecycle.ViewModel;

import com.example.esyanev.proto1.data.Resource;
import com.example.esyanev.proto1.data.local.entity.PostEntity;
import com.example.esyanev.proto1.interactors.LoadPostsUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by esyanev on 22/01/18.
 */

public class PostsViewModel extends ViewModel {

    private LoadPostsUseCase loadPostsUseCase;

    @Inject
    public PostsViewModel(LoadPostsUseCase loadPostsUseCase) {
        this.loadPostsUseCase = loadPostsUseCase;
    }

    public LiveData<Resource<List<PostEntity>>> getPostsLiveData(LoadPostsUseCase.REQUEST_STRATEGY strategy) {
        return LiveDataReactiveStreams.fromPublisher(loadPostsUseCase.execute(strategy)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()));
    }
}
