package com.s.karpardaz.user.data;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.base.concurrent.AppExecutors;
import com.s.karpardaz.base.model.BaseResponse;
import com.s.karpardaz.base.model.Login;

import java.net.HttpURLConnection;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

import static com.s.karpardaz.base.di.NetworkUtil.isResponseSuccessful;
import static com.s.karpardaz.base.di.NetworkUtil.produceUnknownException;
import static com.s.karpardaz.base.util.AppUtil.isAnyEmpty;
import static java.util.Objects.requireNonNull;

@EverythingIsNonNull
public class LoginRepository implements LoginDataSource {

    private final LoginDao mDao;
    private final AppExecutors mExecutor;

    private final LoginService mService;

    @Inject
    public LoginRepository(@NonNull LoginDao dao, @NonNull AppExecutors executors,
        @NonNull LoginService service) {
        mDao = requireNonNull(dao);
        mExecutor = requireNonNull(executors);
        mService = requireNonNull(service);
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
    public void insertLogin(@NonNull Login login, @NonNull BaseCallback<Void> callback) {
        requireNonNull(login);
        requireNonNull(callback);
        mExecutor.getDiskIo().execute(() -> {
            long insertedLoginId = mDao.insert(login);
            mExecutor.getMainThread().execute(() -> {
                if (insertedLoginId > 0)
                    callback.onSuccess();
                else
                    callback.onFailure(new Throwable("Could not get login Info."));
            });
        });
    }

    @Override
    public void login(@NonNull String loginPhrase, @NonNull LoginCallback loginCallback) {
        if (TextUtils.isEmpty(loginPhrase)) throw new NullPointerException();
        requireNonNull(loginCallback);
        mService.login(loginPhrase).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (isResponseSuccessful(response)) loginCallback.onSuccess(response.body());
                else if (response.code() == HttpURLConnection.HTTP_NOT_FOUND)
                    loginCallback.informationNotFound();
                else
                    onFailure(call, produceUnknownException(response.code(), response.errorBody()));
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                loginCallback.onFailure(t);
            }
        });

    }

    @Override
    public void RequestPasswordChange(@NonNull String email,
        @NonNull BaseCallback<String> callback) {
        if (TextUtils.isEmpty(email)) throw new NullPointerException();
        requireNonNull(callback);

        mService.RequestPasswordChange(email).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (isResponseSuccessful(response)) callback.onSuccess(null);
                else
                    onFailure(call, produceUnknownException(response.code(), response.errorBody()));
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(t);
            }
        });

    }

    @Override
    public void sendRecoveryCode(@NonNull String recoveryToken, @NonNull String verificationCode,
        @NonNull SendRecoveryCodeCallback callback) {
        if (isAnyEmpty(recoveryToken, verificationCode)) throw new NullPointerException();
        requireNonNull(callback);

        mService.sendRecoveryCode(recoveryToken, verificationCode).enqueue(
            new Callback<BaseResponse<String>>() {
                @Override
                public void onResponse(Call<BaseResponse<String>> call,
                    Response<BaseResponse<String>> response) {
                    if (isResponseSuccessful(response))
                        callback.onSuccess(response.body());
                    else if (response.code() == HttpURLConnection.HTTP_NOT_FOUND)
                        callback.notFound();
                    else if (response.code() == HttpURLConnection.HTTP_BAD_REQUEST)
                        callback.codeExpired();
                    else onFailure(call,
                            produceUnknownException(response.code(), response.errorBody()));
                }

                @Override
                public void onFailure(Call<BaseResponse<String>> call, Throwable t) {
                    callback.onFailure(t);
                }
            });
    }

    @Override
    public void resetPassword(@NonNull String recoveryToken, @NonNull String loginPhrase,
        @NonNull ResetPasswordCallback callback) {
        if (isAnyEmpty(recoveryToken, loginPhrase)) throw new NullPointerException();
        requireNonNull(callback);

        mService.resetPassword(recoveryToken, loginPhrase).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (isResponseSuccessful(response))
                    callback.onSuccess(null);
                else if (response.code() == HttpURLConnection.HTTP_NOT_FOUND ||
                    response.code() == HttpURLConnection.HTTP_NOT_ACCEPTABLE ||
                    response.code() == HttpURLConnection.HTTP_BAD_REQUEST)
                    callback.notFound();
                else onFailure(call, produceUnknownException(
                        response.code(), response.errorBody()));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    @Override
    public void clearLoginInfo(@NonNull BaseCallback<Void> callback) {
        mExecutor.getDiskIo().execute(() -> {
            mDao.clearTable();
            mExecutor.getMainThread().execute(callback::onSuccess);
        });
    }

}
