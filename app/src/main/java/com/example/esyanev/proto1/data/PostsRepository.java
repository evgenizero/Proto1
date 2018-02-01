package com.example.esyanev.proto1.data;

import com.example.esyanev.proto1.data.local.dao.PostsDao;
import com.example.esyanev.proto1.data.local.dao.PostsEncryptedDao;
import com.example.esyanev.proto1.data.local.entity.PostEntity;
import com.example.esyanev.proto1.data.remote.PostsCallbackService;
import com.example.esyanev.proto1.data.remote.PostsService;
import com.example.esyanev.proto1.errors.ConnectionError;
import com.example.esyanev.proto1.errors.TimeoutError;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by esyanev on 22/01/18.
 */

public class PostsRepository {

    private Maybe<List<PostEntity>> postsDB;
    private Maybe<List<PostEntity>> postsEncryptedDB;
    private Flowable<List<PostEntity>> postsNetwork;
    private final PostsService postsService;
    private Flowable<List<PostEntity>> postsNetworkCallback;

    @Inject
    public PostsRepository(PostsDao postsDao,
                           PostsEncryptedDao encryptedDao,
                           PostsService postsService,
                           PostsCallbackService postsCallbackService) {
        postsDB = postsDao.getMovies();
        postsEncryptedDB = encryptedDao.getMovies()
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
                });
        this.postsService = postsService;

        postsNetworkCallback = Flowable.create(emitter ->
                    postsCallbackService.loadPosts().enqueue(new Callback<List<PostEntity>>() {
                        @Override
                        public void onResponse(Call<List<PostEntity>> call, Response<List<PostEntity>> response) {
                            emitter.onNext(response.body());
                        }

                        @Override
                        public void onFailure(Call<List<PostEntity>> call, Throwable t) {
                        }
                    })
               , BackpressureStrategy.BUFFER);
    }

    public Flowable<Resource<List<PostEntity>>> networkPosts() {
        return postsService.loadPosts()
                .doOnSubscribe(subscription -> Resource.loading())
                .doOnNext(data -> {
                    throw new ConnectionError("not resource found");
                })
                .map(Resource::success)
                .onErrorReturn(Resource::error);

    }
    public Flowable<Resource<List<PostEntity>>> networkCallbackPosts() {
        return postsNetworkCallback
                .map(Resource::success);
    }

    public Flowable<Resource<List<PostEntity>>> networkDB() {
        return Flowable.concat(postsDB.toFlowable(), postsNetwork)
                .map(Resource::success);
    }

    public Flowable<Resource<List<PostEntity>>> networkEncryptedDB() {
        return Flowable.concat(postsEncryptedDB.toFlowable(), postsNetwork)
                .map(Resource::success);
    }
}
