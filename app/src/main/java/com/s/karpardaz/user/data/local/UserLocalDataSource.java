package com.s.karpardaz.user.data.local;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.base.NotImplementedException;
import com.s.karpardaz.base.concurrent.AppExecutors;
import com.s.karpardaz.user.data.UserDataSource;
import com.s.karpardaz.user.model.Login;
import com.s.karpardaz.user.model.User;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Maybe;

import static java.util.Objects.requireNonNull;

public class UserLocalDataSource implements UserDataSource {

    private final UserDao mUserDao;
    private final LoginDao mLoginDao;

    private final AppExecutors mExecutor;

    @Inject
    UserLocalDataSource(@NonNull UserDao userDao, @NonNull LoginDao loginDao,
            @NonNull AppExecutors executors) {
        mUserDao = requireNonNull(userDao);
        mLoginDao = requireNonNull(loginDao);
        mExecutor = requireNonNull(executors);
    }


    @Override
    public Maybe<Login> getLoggedInUser() {
        return mLoginDao.get();
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
            callback.onFailure(new Throwable("Data Not Saved."));
        });
    }

    @Override
    public void insertLogin(@NonNull Login login,
            @NonNull BaseCallback<String> callback) {
        //TODO: handle login insertion properly
        mExecutor.getDiskIo().execute(() -> {
            mLoginDao.insert(login);
        });
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
    public void login(@NonNull String loginPhrase,
            @NonNull LoginCallback loginCallback) {

    }

    @Override
    public void register(@NonNull User user, @NonNull RegisterCallback callback) {
        throw new NotImplementedException();
    }

}
