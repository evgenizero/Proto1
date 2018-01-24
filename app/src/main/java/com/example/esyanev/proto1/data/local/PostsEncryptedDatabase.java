package com.example.esyanev.proto1.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.esyanev.proto1.data.local.dao.PostsDao;
import com.example.esyanev.proto1.data.local.dao.PostsEncryptedDao;
import com.example.esyanev.proto1.data.local.entity.PostEntity;

/**
 * Created by esyanev on 23/01/18.
 */

@Database(entities = {PostEntity.class}, version = 1)
public abstract class PostsEncryptedDatabase extends RoomDatabase{

    public abstract PostsEncryptedDao postsDao();
}
