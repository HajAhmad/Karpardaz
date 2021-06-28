package com.s.karpardaz.cost.di;

import androidx.annotation.NonNull;

import com.s.karpardaz.cost.CostContract;
import com.s.karpardaz.cost.CostPresenter;
import com.s.karpardaz.cost.data.CostDataSource;
import com.s.karpardaz.cost.data.CostRepository;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class CostModule {

    @Binds
    abstract CostDataSource bindRepository(@NonNull CostRepository repository);

    @Binds
    abstract CostContract.Presenter bindPresenter(@NonNull CostPresenter presenter);

}
