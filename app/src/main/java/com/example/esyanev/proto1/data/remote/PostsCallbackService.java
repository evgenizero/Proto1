package com.example.esyanev.proto1.data.remote;

import com.example.esyanev.proto1.data.local.entity.PostEntity;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

/**
 * Created by esyanev on 22/01/18.
 */

public interface PostsCallbackService {

    @GET("posts")
    Call<List<PostEntity>> loadPosts();
}
