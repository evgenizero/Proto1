package com.example.esyanev.proto1.interactors;

import com.example.esyanev.proto1.data.PostsRepository;
import com.example.esyanev.proto1.data.Resource;
import com.example.esyanev.proto1.data.local.entity.PostEntity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by esyanev on 23/01/18.
 */

public class LoadPostsUseCase extends UseCase<LoadPostsUseCase.REQUEST_STRATEGY, Resource<List<PostEntity>>> {

    public enum REQUEST_STRATEGY {
        NETWORK_ONLY,
        NETWORK_ONLY_CALLBACK,
        NETWORK_DB,
        NETWORK_ENCRYPTED_DB
    }

    private PostsRepository postsRepository;

    @Inject
    public LoadPostsUseCase(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @Override
    public Flowable<Resource<List<PostEntity>>> execute(REQUEST_STRATEGY strategy) {
        switch (strategy) {
            case NETWORK_ONLY:
                return postsRepository.networkPosts();
            case NETWORK_ONLY_CALLBACK:
                return postsRepository.networkCallbackPosts();
            case NETWORK_DB:
                return postsRepository.networkDB();
            case NETWORK_ENCRYPTED_DB:
                return postsRepository.networkEncryptedDB();
        }

        return postsRepository.getPosts();
    }
}
