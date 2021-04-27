package com.s.karpardaz.user;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.di.NetworkInjection;
import com.s.karpardaz.user.data.LoginDataSource;
import com.s.karpardaz.user.data.LoginRepository;
import com.s.karpardaz.user.data.UserDataSource;
import com.s.karpardaz.user.data.UserRepository;
import com.s.karpardaz.user.data.LoginService;
import com.s.karpardaz.user.data.UserService;

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

    @Binds
    public abstract LoginDataSource bindLoginRepository(@NonNull LoginRepository repository);

    @Binds
    public abstract RegisterContract.Presenter bindRegisterPresenter(
            @NonNull RegisterPresenter presenter);

    @Binds
    public abstract LoginContract.Presenter bindLoginPresenter(@NonNull LoginPresenter presenter);

    @Binds
    public abstract RecoveryPasswordContract.Presenter bindRecoveryPasswordPresenter(@NonNull
        RecoveryPasswordPresenter presenter);

    @Provides
    public static UserService provideUserService() {
        return NetworkInjection.provideService(UserService.class);
    }

    @Provides
    public static LoginService provideLoginService(){
        return NetworkInjection.provideService(LoginService.class);
    }

}
