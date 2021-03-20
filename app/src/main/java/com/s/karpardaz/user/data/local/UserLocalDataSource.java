package com.s.karpardaz.user.data.local;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.base.NotImplementedException;
import com.s.karpardaz.base.concurrent.AppExecutors;
import com.s.karpardaz.user.data.UserDataSource;
import com.s.karpardaz.user.model.Login;
import com.s.karpardaz.user.model.User;

import static java.util.Objects.requireNonNull;

public class UserLocalDataSource implements UserDataSource {

    private static volatile UserLocalDataSource sInstance;

    private final AppExecutors mExecutor;

    private final UserDao mUserDao;
    private final LoginDao mLoginDao;

    private UserLocalDataSource(@NonNull UserDao userDao, @NonNull LoginDao loginDao,
            @NonNull AppExecutors executors) {
        this.mUserDao = requireNonNull(userDao);
        mLoginDao = requireNonNull(loginDao);
        mExecutor = requireNonNull(executors);
    }

    public static UserLocalDataSource getInstance(@NonNull UserDao userDao,
            @NonNull LoginDao loginDao,
            @NonNull AppExecutors executors) {
        if (sInstance == null) {
            synchronized (UserLocalDataSource.class) {
                if (sInstance == null) {
                    sInstance = new UserLocalDataSource(userDao, loginDao, executors);
                }
            }
        }
        return sInstance;
    }

    @Override
    public void getLoggedInUser() {
        mLoginDao.get();
    }

    @Override
    public void getUser(@NonNull String uuid) {
        mUserDao.get(uuid);
    }

    @Override
    public void getUser(@NonNull Long id) {

    }

    @Override
    public void getUser(@NonNull String email, @NonNull String password) {
        mUserDao.get(email, password);
    }

    @Override
    public void insertUser(final @NonNull User user, @NonNull final BaseCallback<String> callback) {
        requireNonNull(user);
        requireNonNull(callback);
        mExecutor.getDiskIo().execute(() -> {
            long insertedUserId = mUserDao.insert(user);
            if (insertedUserId > -1) {
                String uuid = mUserDao.getInsertedId(insertedUserId);
                callback.onSuccess(uuid);
                return;
            }
            callback.onFailure();
        });
    }

    @Override
    public void insertLogin(@NonNull Login login,
            @NonNull BaseCallback<String> callback) {
        mLoginDao.insert(login);
    }

    @Override
    public void deleteUser(@NonNull User user) {
        mUserDao.delete(user);
    }

    @Override
    public void deleteLogin(@NonNull Login login) {
        mLoginDao.delete(login);
    }

    @Override
    public void login(@NonNull String email, @NonNull String password) {

    }

    @Override
    public LiveData<String> register(@Nullable String name,
            @NonNull String email,
            @NonNull String password,
            @NonNull String currentDateTime) {
        throw new NotImplementedException();
    }
}
