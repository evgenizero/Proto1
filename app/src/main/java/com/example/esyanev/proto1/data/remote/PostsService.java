package com.example.esyanev.proto1.data.remote;

import com.example.esyanev.proto1.data.local.entity.PostEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by esyanev on 22/01/18.
 */

public interface PostsService {

    @GET("posts")
    Flowable<List<PostEntity>> loadPosts();
}
