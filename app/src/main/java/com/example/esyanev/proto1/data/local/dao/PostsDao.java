package com.example.esyanev.proto1.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.esyanev.proto1.data.local.entity.PostEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

/**
 * Created by esyanev on 22/01/18.
 */

@Dao
public interface PostsDao {

    @Query("SELECT * FROM posts")
    Maybe<List<PostEntity>> getMovies();

    @Query("SELECT * FROM posts WHERE id=:id")
    Flowable<PostEntity> getMovie(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void savePosts(List<PostEntity> postEntities);
}
