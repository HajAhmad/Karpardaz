package com.s.karpardaz.user.data;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.base.NotImplementedException;
import com.s.karpardaz.base.concurrent.AppExecutors;
import com.s.karpardaz.base.model.User;

import javax.inject.Inject;

import static java.util.Objects.requireNonNull;

public class UserRepository implements UserDataSource {

    private final UserDao mUserDao;
    private final AppExecutors mExecutor;

    @Inject
    public UserRepository(@NonNull UserDao userDao, @NonNull AppExecutors executors) {
        mUserDao = requireNonNull(userDao);
        mExecutor = requireNonNull(executors);
    }

    @Override
    public void getUser(@NonNull String uuid) {
        throw new NotImplementedException();
    }

    @Override
    public void getUser(@NonNull Long id) {
        throw new NotImplementedException();
    }

    @Override
    public void getUser(@NonNull String email, @NonNull String password) {
        throw new NotImplementedException();
    }

    @Override
    public void insertUser(@NonNull User user, @NonNull BaseCallback<String> callback) {
        mExecutor.getDiskIo().execute(() -> {
            long insertedUserId = mUserDao.insert(user);
            mExecutor.getMainThread().execute(() -> {
                if (insertedUserId > -1) {
                    String uuid = mUserDao.getInsertedId(insertedUserId);
                    callback.onSuccess(uuid);
                    return;
                }
                callback.onFailure(new Throwable("Data Not Saved."));
            });
        });
    }


    @Override
    public void deleteUser(@NonNull User user) {
        throw new NotImplementedException();
    }

    @Override
    public void register(@NonNull User user, @NonNull RegisterCallback callback) {
        requireNonNull(user);
        requireNonNull(callback);
        mExecutor.getDiskIo().execute(() -> {
            Long returnedId = mUserDao.insert(user);
            mExecutor.getMainThread().execute(() -> {
                if (returnedId == -1) callback.onFailure(new Throwable());
                else callback.onSuccess(user.getUuid());
            });
        });

    }

}
