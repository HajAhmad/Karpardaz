package com.s.karpardaz.user;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.di.NetworkInjection;
import com.s.karpardaz.base.di.scope.Local;
import com.s.karpardaz.base.di.scope.Remote;
import com.s.karpardaz.user.data.UserDataSource;
import com.s.karpardaz.user.data.UserRepository;
import com.s.karpardaz.user.data.local.UserLocalDataSource;
import com.s.karpardaz.user.data.remote.UserRemoteDataSource;
import com.s.karpardaz.user.data.remote.UserService;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.FragmentComponent;

@Module
@InstallIn(value = {ActivityComponent.class, FragmentComponent.class})
public abstract class UserModule {

    @Binds
    public abstract UserDataSource bindRepository(@NonNull UserRepository repository);

    @Local
    @Binds
    public abstract UserDataSource bindLocalDataSource(@NonNull UserLocalDataSource dataSource);

    @Remote
    @Binds
    public abstract UserDataSource bindRemoteDataSource(@NonNull UserRemoteDataSource dataSource);

    @Binds
    public abstract RegisterContract.Presenter bindRegisterPresenter(
            @NonNull RegisterPresenter presenter);

    @Binds
    public abstract LoginContract.Presenter bindLoginPresenter(@NonNull LoginPresenter presenter);

    @Provides
    public static UserService provideUserService() {
        return NetworkInjection.provideService(UserService.class);
    }

}
