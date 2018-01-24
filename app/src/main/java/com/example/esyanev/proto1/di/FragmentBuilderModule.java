package com.example.esyanev.proto1.di;

import com.example.esyanev.proto1.ui.main.ChooseFragment;
import com.example.esyanev.proto1.ui.main.PostListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract PostListFragment contributeMovieListFragment();

    @ContributesAndroidInjector
    abstract ChooseFragment contributeChooseFragment();
}
