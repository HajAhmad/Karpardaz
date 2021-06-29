package com.s.karpardaz.base.di;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.datasource.KarpardazDatabase;
import com.s.karpardaz.cost.data.CostDao;
import com.s.karpardaz.stock.data.DefaultStockDao;
import com.s.karpardaz.stock.data.StockDao;
import com.s.karpardaz.user.data.LoginDao;
import com.s.karpardaz.user.data.UserDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

import static java.util.Objects.requireNonNull;

@Module
@InstallIn(SingletonComponent.class)
public abstract class DbModule {

    @Provides
    @Singleton
    static UserDao provideUserDao(@NonNull KarpardazDatabase database) {
        return requireNonNull(database).userDao();
    }

    @Provides
    @Singleton
    static LoginDao provideLoginDao(@NonNull KarpardazDatabase database) {
        return requireNonNull(database).loginDao();
    }

    @Provides
    @Singleton
    static CostDao provideCostDao(@NonNull KarpardazDatabase database) {
        return requireNonNull(database).costDao();
    }

    @Provides
    @Singleton
    static StockDao provideStockDao(@NonNull KarpardazDatabase database) {
        return requireNonNull(database).stockDao();
    }

    @Provides
    @Singleton
    static DefaultStockDao provideDefaultStockDao(@NonNull KarpardazDatabase database){
        return requireNonNull(database).defaultStockDao();
    }

}
