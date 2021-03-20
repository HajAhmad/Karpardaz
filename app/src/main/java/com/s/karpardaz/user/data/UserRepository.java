package com.s.karpardaz.user.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.user.model.Login;
import com.s.karpardaz.user.model.User;

import java.util.Objects;

public class UserRepository implements UserDataSource {

    private final UserDataSource mLocalDataSource;
    private final UserDataSource mRemoteDataSource;

    private UserRepository(@NonNull UserDataSource localDataSource,
            @NonNull UserDataSource remoteDataSource) {
        this.mLocalDataSource = Objects.requireNonNull(localDataSource);
        this.mRemoteDataSource = Objects.requireNonNull(remoteDataSource);
    }

    private volatile static UserRepository sInstance;

    public static UserRepository getInstance(UserDataSource localDataSource,
            UserDataSource remoteDataSource) {
        if (sInstance == null) {
            synchronized (UserRepository.class) {
                if (sInstance == null)
                    sInstance = new UserRepository(localDataSource, remoteDataSource);
            }
        }
        return sInstance;
    }

    @Override
    public void getLoggedInUser() {
        mLocalDataSource.getLoggedInUser();
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
    public void insertLogin(@NonNull Login login,
            @NonNull BaseCallback<String> callback) {

    }

    @Override
    public void deleteUser(@NonNull User user) {

    }

    @Override
    public void deleteLogin(@NonNull Login login) {

    }

    @Override
    public void login(@NonNull String email, @NonNull String password) {

    }

    @Override
    public LiveData<String> register(@Nullable String name,
            @NonNull String email,
            @NonNull String password,
            @NonNull String currentDateTime) {
        mRemoteDataSource.register(name, email, password, currentDateTime);
        return null;
    }
}
