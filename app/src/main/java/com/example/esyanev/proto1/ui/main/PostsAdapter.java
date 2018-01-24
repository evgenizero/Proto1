package com.example.esyanev.proto1.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.esyanev.proto1.data.local.entity.PostEntity;
import com.example.esyanev.proto1.databinding.ItemPostListBinding;
import com.example.esyanev.proto1.ui.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

/**
 * Created by esyanev on 22/01/18.
 */

public class PostsAdapter extends BaseAdapter<PostsAdapter.PostViewHolder, PostEntity> {

    private List<PostEntity> postEntities;
    private final PostListCallback postListCallback;

    public PostsAdapter(@Nonnull PostListCallback callback) {
        postEntities = new ArrayList<>();
        this.postListCallback = callback;
    }

    @Override
    public void setData(List<PostEntity> postEntities) {
        this.postEntities = postEntities;
        notifyDataSetChanged();
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return PostViewHolder.create(LayoutInflater.from(parent.getContext()), parent, postListCallback);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.onBind(postEntities.get(position));
    }

    @Override
    public int getItemCount() {
        return postEntities.size();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {

        private final ItemPostListBinding binding;

        public PostViewHolder(ItemPostListBinding itemPostListBinding, PostListCallback callback) {
            super(itemPostListBinding.getRoot());
            this.binding = itemPostListBinding;
            binding.getRoot().setOnClickListener(v ->
                callback.onPostClicked(binding.getPost(), binding.postTitle));
        }

        public static PostViewHolder create(LayoutInflater inflater, ViewGroup parent, PostListCallback callback) {
            ItemPostListBinding itemPostListBinding = ItemPostListBinding.inflate(inflater, parent, false);
            return new PostViewHolder(itemPostListBinding, callback);
        }

        public void onBind(PostEntity postEntity) {
            binding.setPost(postEntity);
            binding.executePendingBindings();
        }
    }
}
