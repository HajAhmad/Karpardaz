package com.s.karpardaz.user.data;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.base.di.scope.Local;
import com.s.karpardaz.base.di.scope.Remote;
import com.s.karpardaz.user.model.Login;
import com.s.karpardaz.user.model.User;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Maybe;

import static java.util.Objects.requireNonNull;

public class UserRepository implements UserDataSource {

    private final UserDataSource mLocalDataSource;
    private final UserDataSource mRemoteDataSource;

    @Inject
    public UserRepository(@NonNull @Local UserDataSource localDataSource,
            @NonNull @Remote UserDataSource remoteDataSource) {
        this.mLocalDataSource = requireNonNull(localDataSource);
        this.mRemoteDataSource = requireNonNull(remoteDataSource);
    }

    @Override
    public Maybe<Login> getLoggedInUser() {
        return mLocalDataSource.getLoggedInUser();
    }

    @Override
    public void getUser(@NonNull String uuid) {

    }

    @Override
    public void getUser(@NonNull Long id) {

    }

    @Override
    public void getUser(@NonNull String email, @NonNull String password) {

    }

    @Override
    public void insertUser(@NonNull User user, @NonNull BaseCallback<String> callback) {

    }

    @Override
    public void insertLogin(@NonNull Login login, @NonNull BaseCallback<String> callback) {
        requireNonNull(login);
        requireNonNull(callback);
        mLocalDataSource.insertLogin(login, callback);
    }

    @Override
    public void deleteUser(@NonNull User user) {

    }

    @Override
    public void deleteLogin(@NonNull Login login) {

    }

    @Override
    public void login(@NonNull String loginPhrase,
            @NonNull LoginCallback loginCallback) {
        requireNonNull(loginPhrase);
        requireNonNull(loginCallback);

        mRemoteDataSource.login(loginPhrase, loginCallback);
    }

    @Override
    public void register(@NonNull User user, @NonNull RegisterCallback callback) {
        mRemoteDataSource.register(requireNonNull(user), requireNonNull(callback));
    }

}
