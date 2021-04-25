package com.s.karpardaz.user.data;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.base.concurrent.AppExecutors;
import com.s.karpardaz.user.model.Login;

import java.net.HttpURLConnection;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Maybe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

import static com.s.karpardaz.base.di.NetworkUtil.isResponseSuccessful;
import static com.s.karpardaz.base.di.NetworkUtil.produceUnknownException;
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
    public Maybe<Login> getLoggedInUser() {
        return mDao.get();
    }

    @Override
    public void insertLogin(@NonNull Login login, @NonNull BaseCallback<Void> callback) {
        requireNonNull(login);
        requireNonNull(callback);
        mExecutor.getDiskIo().execute(() -> {
            long insertedLoginId = mDao.insert(login);
            if (insertedLoginId > 0)
                callback.onSuccess(null);
            else
                callback.onFailure(new Throwable("Could not get login Info."));
        });
    }

    @Override
    public void deleteLogin(@NonNull Login login) {
        requireNonNull(login);
        mDao.delete(login);
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
                    loginCallback.onFailure(produceUnknownException(response.code()));
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                loginCallback.onFailure(t);
            }
        });

    }

    @Override
    public void RequestPasswordChange(@NonNull String email, @NonNull BaseCallback<Void> callback) {
        if (TextUtils.isEmpty(email)) throw new NullPointerException();
        requireNonNull(callback);

        mService.RequestPasswordChange(email).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (isResponseSuccessful(response)) callback.onSuccess(null);
                else callback.onFailure(produceUnknownException(response.code()));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onFailure(t);
            }
        });

    }

}
