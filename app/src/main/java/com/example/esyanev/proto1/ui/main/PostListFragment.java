package com.example.esyanev.proto1.ui.main;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.esyanev.proto1.R;
import com.example.esyanev.proto1.data.Resource;
import com.example.esyanev.proto1.data.Status;
import com.example.esyanev.proto1.data.local.entity.PostEntity;
import com.example.esyanev.proto1.databinding.FragmentPostsListBinding;
import com.example.esyanev.proto1.interactors.LoadPostsUseCase;
import com.example.esyanev.proto1.ui.BaseFragment;
import com.example.esyanev.proto1.viewmodel.PostsViewModel;

/**
 * Created by esyanev on 22/01/18.
 */

public class PostListFragment extends BaseFragment<PostsViewModel, FragmentPostsListBinding> implements PostListCallback {

    public static PostListFragment newInstance(LoadPostsUseCase.REQUEST_STRATEGY strategy) {
        Bundle args = new Bundle();
        args.putSerializable("strategy", strategy);
        PostListFragment postListFragment = new PostListFragment();
        postListFragment.setArguments(args);
        return postListFragment;
    }

    @Override
    public void onPostClicked(PostEntity postEntity, View sharedView) {

    }

    @Override
    public Class<PostsViewModel> getViewModel() {
        return PostsViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_posts_list;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataBinding.recyclerView.setAdapter(new PostsAdapter(this));

        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel.getSomeEvent()
                .observe(this, message -> Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show());

        dataBinding.setViewModel(viewModel);

        LoadPostsUseCase.REQUEST_STRATEGY strategy = (LoadPostsUseCase.REQUEST_STRATEGY) getArguments().getSerializable("strategy");
        viewModel.loadPosts(strategy);
    }

}
