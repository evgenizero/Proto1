package com.example.esyanev.proto1.data;

import android.util.Log;

import com.example.esyanev.proto1.data.local.dao.PostsDao;
import com.example.esyanev.proto1.data.local.dao.PostsEncryptedDao;
import com.example.esyanev.proto1.data.local.entity.PostEntity;
import com.example.esyanev.proto1.data.remote.PostsCallbackService;
import com.example.esyanev.proto1.data.remote.PostsService;
import com.example.esyanev.proto1.interactors.LoadPostsUseCase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by esyanev on 22/01/18.
 */

public class PostsRepository {

    private Maybe<Resource<List<PostEntity>>> postsCache;
    private Maybe<Resource<List<PostEntity>>> postsDB;
    private Maybe<Resource<List<PostEntity>>> postsEncryptedDB;
    private Flowable<Resource<List<PostEntity>>> postsNetwork;
    private Flowable<Resource<List<PostEntity>>> postsNetworkCallback;

    private long currentTime;

    @Inject
    public PostsRepository(PostsDao postsDao,
                           PostsEncryptedDao encryptedDao,
                           PostsService postsService,
                           PostsCallbackService postsCallbackService) {
        postsDB = postsDao.getMovies()
                .doOnSubscribe(disposable -> currentTime = System.nanoTime())
                .map(postEntities -> Resource.success(postEntities, "DB for " + (System.nanoTime() - currentTime)))
                .subscribeOn(Schedulers.computation());

        postsEncryptedDB = encryptedDao.getMovies()
                .doOnSubscribe(disposable -> currentTime = System.nanoTime())
                .map(postEntities -> Resource.success(postEntities, "DB encrypted" + (System.nanoTime() - currentTime)))
                .subscribeOn(Schedulers.computation());


        postsNetwork = postsService.loadPosts()
                .doOnNext(data -> {
                    data.addAll(data);
                    data.addAll(data);
                    data.addAll(data);
                    data.addAll(data);
                    data.addAll(data);
                    postsDao.savePosts(data);
                    encryptedDao.savePosts(data);
                })
                .map(postEntities -> Resource.success(postEntities, "Network"))
                .subscribeOn(Schedulers.io());

        postsNetworkCallback = Flowable.create(emitter ->
                    postsCallbackService.loadPosts().enqueue(new Callback<List<PostEntity>>() {
                        @Override
                        public void onResponse(Call<List<PostEntity>> call, Response<List<PostEntity>> response) {
                            emitter.onNext(Resource.success(response.body(), "Network callback"));
                        }

                        @Override
                        public void onFailure(Call<List<PostEntity>> call, Throwable t) {
                        }
                    })
               , BackpressureStrategy.BUFFER);
    }

    public Flowable<Resource<List<PostEntity>>> getPosts() {
        return Flowable.concat(postsCache.toFlowable(), postsDB.toFlowable(), postsNetwork);
    }

    public Flowable<Resource<List<PostEntity>>> networkPosts() {
        return Flowable.create(emitter -> postsNetwork
                    .doOnSubscribe(subscription -> emitter.onNext(Resource.loading(null)))
                    .subscribe(emitter::onNext),
                BackpressureStrategy.BUFFER);
    }
    public Flowable<Resource<List<PostEntity>>> networkCallbackPosts() {
        return Flowable.create(emitter -> postsNetworkCallback
                        .doOnSubscribe(subscription -> emitter.onNext(Resource.loading(null)))
                        .subscribe(emitter::onNext),
                BackpressureStrategy.BUFFER);
    }

    public Flowable<Resource<List<PostEntity>>> networkDB() {
        return Flowable.create(emitter -> Flowable.concat(postsDB.toFlowable(), postsNetwork)
                    .doOnSubscribe(subscription -> emitter.onNext(Resource.loading(null)))
                    .subscribe(listResource -> emitter.onNext(listResource)),
                BackpressureStrategy.BUFFER);
    }

    public Flowable<Resource<List<PostEntity>>> networkEncryptedDB() {
        return Flowable.create(emitter -> Flowable.concat(postsEncryptedDB.toFlowable(), postsNetwork)
                    .doOnSubscribe(subscription -> emitter.onNext(Resource.loading(null)))
                    .subscribe(listResource -> emitter.onNext(listResource)),
                BackpressureStrategy.BUFFER);
    }
}
