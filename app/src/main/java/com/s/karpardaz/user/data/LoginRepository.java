package com.s.karpardaz.user.data;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.base.concurrent.AppExecutors;
import com.s.karpardaz.base.model.Login;
import com.s.karpardaz.base.model.User;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import retrofit2.internal.EverythingIsNonNull;

import static com.s.karpardaz.base.util.AppUtil.isAnyEmpty;
import static java.util.Objects.requireNonNull;

@EverythingIsNonNull
public class LoginRepository implements LoginDataSource {

    private final LoginDao mDao;
    private final UserDao mUserDao;
    private final AppExecutors mExecutor;

    @Inject
    public LoginRepository(@NonNull LoginDao dao, @NonNull AppExecutors executors,
        @NonNull UserDao userDao) {
        mDao = requireNonNull(dao);
        mExecutor = requireNonNull(executors);
        mUserDao = requireNonNull(userDao);
    }

    @Override
    public void getLoggedInUser(@Nonnull GetLoggedInUserCallback callback) {
        requireNonNull(callback);
        mExecutor.getDiskIo().execute(() -> {
            Login login = mDao.get();
            mExecutor.getMainThread().execute(() -> {
                if (login == null) {
                    callback.notFound();
                } else {
                    callback.onSuccess(login);
                }
            });
        });
    }

    @Override
    public void login(@NonNull String email, @NonNull String password, @NonNull String createdAt,
        @NonNull LoginCallback callback) {
        requireNonNull(email);
        requireNonNull(callback);

        mExecutor.getDiskIo().execute(() -> {
            User user = mUserDao.get(email, password);
            mDao.clearTable();

            if (user != null)
                mDao.insert(new Login(user.getUuid(), createdAt));

            mExecutor.getMainThread().execute(() -> {
                if (user == null)
                    callback.onFailure(new Throwable("Could not get login Info."));
                else
                    callback.onSuccess(user.getUuid());
            });
        });

        //        mService.login(login).enqueue(new Callback<String>() {
        //            @Override
        //            public void onResponse(Call<String> call, Response<String> response) {
        //                if (isResponseSuccessful(response)) loginCallback.onSuccess(response.body());
        //                else if (response.code() == HttpURLConnection.HTTP_NOT_FOUND)
        //                    loginCallback.informationNotFound();
        //                else
        //                    onFailure(call, produceUnknownException(response.code(), response.errorBody()));
        //            }
        //
        //            @Override
        //            public void onFailure(Call<String> call, Throwable t) {
        //                loginCallback.onFailure(t);
        //            }
        //        });

    }

    @Override
    public void RequestPasswordChange(@NonNull String email,
        @NonNull BaseCallback<String> callback) {
        if (TextUtils.isEmpty(email)) throw new NullPointerException();
        requireNonNull(callback);
        //
        //        mService.RequestPasswordChange(email).enqueue(new Callback<String>() {
        //            @Override
        //            public void onResponse(Call<String> call, Response<String> response) {
        //                if (isResponseSuccessful(response)) callback.onSuccess();
        //                else
        //                    onFailure(call, produceUnknownException(response.code(), response.errorBody()));
        //            }
        //
        //            @Override
        //            public void onFailure(Call<String> call, Throwable t) {
        //                callback.onFailure(t);
        //            }
        //        });

    }

    @Override
    public void sendRecoveryCode(@NonNull String recoveryToken, @NonNull String verificationCode,
        @NonNull SendRecoveryCodeCallback callback) {
        if (isAnyEmpty(recoveryToken, verificationCode)) throw new NullPointerException();
        requireNonNull(callback);

        //        mService.sendRecoveryCode(recoveryToken, verificationCode).enqueue(
        //            new Callback<BaseResponse<String>>() {
        //                @Override
        //                public void onResponse(Call<BaseResponse<String>> call,
        //                    Response<BaseResponse<String>> response) {
        //                    if (isResponseSuccessful(response))
        //                        callback.onSuccess(response.body());
        //                    else if (response.code() == HttpURLConnection.HTTP_NOT_FOUND)
        //                        callback.notFound();
        //                    else if (response.code() == HttpURLConnection.HTTP_BAD_REQUEST)
        //                        callback.codeExpired();
        //                    else onFailure(call,
        //                            produceUnknownException(response.code(), response.errorBody()));
        //                }
        //
        //                @Override
        //                public void onFailure(Call<BaseResponse<String>> call, Throwable t) {
        //                    callback.onFailure(t);
        //                }
        //            });
    }

    @Override
    public void resetPassword(@NonNull String recoveryToken, @NonNull Login login,
        @NonNull ResetPasswordCallback callback) {
        requireNonNull(login);
        requireNonNull(callback);

        //        mService.resetPassword(recoveryToken, login).enqueue(new Callback<Void>() {
        //            @Override
        //            public void onResponse(Call<Void> call, Response<Void> response) {
        //                if (isResponseSuccessful(response))
        //                    callback.onSuccess(null);
        //                else if (response.code() == HttpURLConnection.HTTP_NOT_FOUND ||
        //                    response.code() == HttpURLConnection.HTTP_NOT_ACCEPTABLE ||
        //                    response.code() == HttpURLConnection.HTTP_BAD_REQUEST)
        //                    callback.notFound();
        //                else onFailure(call, produceUnknownException(
        //                        response.code(), response.errorBody()));
        //            }
        //
        //            @Override
        //            public void onFailure(Call<Void> call, Throwable t) {
        //                callback.onFailure(t);
        //            }
        //        });
    }

    @Override
    public void clearLoginInfo(@NonNull BaseCallback<Void> callback) {
        mExecutor.getDiskIo().execute(() -> {
            mDao.clearTable();
            mExecutor.getMainThread().execute(callback::onSuccess);
        });
    }

}
