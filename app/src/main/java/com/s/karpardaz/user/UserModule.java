package com.s.karpardaz.user;

import android.app.Application;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.concurrent.AppExecutors;
import com.s.karpardaz.base.datasource.DataModule;
import com.s.karpardaz.base.datasource.KarpardazDatabase;
import com.s.karpardaz.base.di.remote.NetworkInjection;
import com.s.karpardaz.user.data.UserDataSource;
import com.s.karpardaz.user.data.local.UserLocalDataSource;
import com.s.karpardaz.user.data.remote.UserRemoteDataSource;
import com.s.karpardaz.user.data.UserRepository;
import com.s.karpardaz.user.data.remote.UserService;

public class UserModule {

    public static UserDataSource provideRepository(@NonNull Application application){
        return UserRepository.getInstance(provideLocalDataSource(application),
                provideRemoteDataSource());
    }

    public static UserDataSource provideLocalDataSource(@NonNull Application application) {
        final KarpardazDatabase database = DataModule.provideDatabase(application);
        return UserLocalDataSource.getInstance(
                database.userDao(),
                database.loginDao(),
                new AppExecutors()
        );
    }

    public static UserDataSource provideRemoteDataSource(){
        return new UserRemoteDataSource(NetworkInjection.provideService(UserService.class));
    }
}
