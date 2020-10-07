package com.s.karpardaz.shared.data.source;

import com.s.karpardaz.base.di.scope.LocalDataSource;
import com.s.karpardaz.base.di.scope.RemoteDataSource;
import com.s.karpardaz.shared.data.source.local.CostLocalDataSource;
import com.s.karpardaz.shared.data.source.remote.CostRemoteDataSource;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class CostModule {

    @RemoteDataSource
    @Binds
    public abstract CostDataSource bindRemoteDataSource(CostRemoteDataSource dataSource);

    @LocalDataSource
    @Binds
    public abstract CostDataSource bindLocalDataSource(CostLocalDataSource dataSource);
}
