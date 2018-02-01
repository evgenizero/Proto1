package com.example.esyanev.proto1.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;
import android.view.View;

import com.example.esyanev.proto1.data.Resource;
import com.example.esyanev.proto1.data.local.entity.PostEntity;
import com.example.esyanev.proto1.errors.Error;
import com.example.esyanev.proto1.errors.GeneralErrorHandler;
import com.example.esyanev.proto1.interactors.Input;
import com.example.esyanev.proto1.interactors.LoadPostsUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by esyanev on 22/01/18.
 */

public class PostsViewModel extends BaseViewModel implements GeneralErrorHandler {

    private LoadPostsUseCase loadPostsUseCase;
    private SingleLiveEvent<String> someEvent = new SingleLiveEvent<>();
    public ObservableField<Resource<List<PostEntity>>> field = new ObservableField<>();

    @Inject
    public PostsViewModel(LoadPostsUseCase loadPostsUseCase) {
        super(loadPostsUseCase);
        this.loadPostsUseCase = loadPostsUseCase;
    }

    public void onSomeButtonClick(View v) {
        someEvent.setValue("Click");
    }

    public void loadPosts(LoadPostsUseCase.REQUEST_STRATEGY strategy) {
        loadPostsUseCase.execute(new Input<>(strategy, AndroidSchedulers.mainThread()),
                new PostsSubscriber());
    }

    public SingleLiveEvent<String> getSomeEvent() {
        return someEvent;
    }

    private class PostsSubscriber extends UseCaseSubscriber<List<PostEntity>> {

        @Override
        public void onNext(Resource<List<PostEntity>> resource) {
            super.onNext(resource);
            field.set(resource);
        }

        @Override
        public boolean onConnectionError(Error error) {
            someEvent.setValue(error.getMessage());
            return true;
        }

    }
}
