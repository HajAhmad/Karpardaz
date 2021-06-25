package com.s.karpardaz.stock.di;

import androidx.annotation.NonNull;

import com.s.karpardaz.stock.StockContract;
import com.s.karpardaz.stock.StockPresenter;
import com.s.karpardaz.stock.data.StockDataSource;
import com.s.karpardaz.stock.data.StockRepository;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class StockModule {

    @Binds
    public abstract StockDataSource bindRepository(@NonNull StockRepository repository);

    @Binds
    public abstract StockContract.Presenter bindPresenter(@NonNull StockPresenter presenter);

}
