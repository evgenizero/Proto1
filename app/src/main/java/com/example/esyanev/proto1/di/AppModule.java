package com.example.esyanev.proto1.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.text.Editable;
import android.text.SpannableStringBuilder;

import com.commonsware.cwac.saferoom.SafeHelperFactory;
import com.example.esyanev.proto1.data.local.PostsDatabase;
import com.example.esyanev.proto1.data.local.PostsEncryptedDatabase;
import com.example.esyanev.proto1.data.local.dao.PostsDao;
import com.example.esyanev.proto1.data.local.dao.PostsEncryptedDao;
import com.example.esyanev.proto1.data.remote.ApiConstants;
import com.example.esyanev.proto1.data.remote.PostsCallbackService;
import com.example.esyanev.proto1.data.remote.PostsService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    PostsService provideRetrofit(Retrofit retrofit) {
        return retrofit.create(PostsService.class);
    }

    @Provides
    @Singleton
    PostsCallbackService provideCallbackRetrofit(Retrofit retrofit) {
        return retrofit.create(PostsCallbackService.class);
    }

    @Provides
    @Singleton
    Retrofit providesRetrofitClient(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(ApiConstants.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    PostsDatabase provideMovieDatabase(Application application) {
        return Room.databaseBuilder(application, PostsDatabase.class, "aa.db").build();
    }

    @Provides
    @Singleton
    PostsEncryptedDatabase providesPostsEncryptedDB(Application application) {
        Editable editable = new SpannableStringBuilder("blablabla");
        SafeHelperFactory factory = SafeHelperFactory.fromUser(editable);
        return Room.databaseBuilder(application, PostsEncryptedDatabase.class, "bb.db")
                .openHelperFactory(factory)
                .build();
    }

    @Provides
    @Singleton
    PostsDao provideMovieDao(PostsDatabase movieDatabase) {
        return movieDatabase.postsDao();
    }

    @Provides
    @Singleton
    PostsEncryptedDao providePostsEncryptedDao(PostsEncryptedDatabase movieDatabase) {
        return movieDatabase.postsDao();
    }
}
