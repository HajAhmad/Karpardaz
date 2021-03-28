package com.s.karpardaz.main;


import androidx.annotation.NonNull;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@Module
@InstallIn(ActivityComponent.class)
public abstract class MainModule {

    @Binds
    public abstract MainContract.Presenter providePresenter(@NonNull MainPresenter presenter);

}
