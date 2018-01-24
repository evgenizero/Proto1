package com.example.esyanev.proto1.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.esyanev.proto1.viewmodel.ChooseViewModel;
import com.example.esyanev.proto1.viewmodel.PostsViewModelFactory;
import com.example.esyanev.proto1.viewmodel.PostsViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel.class)
    abstract ViewModel bindsPostViewModel(PostsViewModel movieListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ChooseViewModel.class)
    abstract ViewModel bindsChooseViewModel(ChooseViewModel chooseViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(PostsViewModelFactory postsViewModelFactory);
}
