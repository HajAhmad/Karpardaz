package com.s.karpardaz.user.data;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.base.NotImplementedException;
import com.s.karpardaz.base.concurrent.AppExecutors;
import com.s.karpardaz.base.model.User;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

import static com.s.karpardaz.base.di.NetworkUtil.isResponseSuccessful;
import static com.s.karpardaz.base.util.AppUtil.produceNetworkException;
import static java.util.Objects.requireNonNull;

@EverythingIsNonNull
public class UserRepository implements UserDataSource {

    private final UserDao mUserDao;
    private final AppExecutors mExecutor;

    private final UserService mUserService;

    @Inject
    public UserRepository(@NonNull UserDao userDao, @NonNull AppExecutors executors,
        @NonNull UserService service) {
        mUserDao = requireNonNull(userDao);
        mExecutor = requireNonNull(executors);

        mUserService = requireNonNull(service);
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
            if (insertedUserId > -1) {
                String uuid = mUserDao.getInsertedId(insertedUserId);
                callback.onSuccess(uuid);
                return;
            }
            callback.onFailure(new Throwable("Data Not Saved."));
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
        mUserService.register(user).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (isResponseSuccessful(response))
                    callback.onSuccess(response.body());
                else
                    callback.onFailure(produceNetworkException(response));
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

}
