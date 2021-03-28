package com.s.karpardaz.user.data.remote;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.base.NotImplementedException;
import com.s.karpardaz.user.data.UserDataSource;
import com.s.karpardaz.user.model.Login;
import com.s.karpardaz.user.model.User;

import java.net.HttpURLConnection;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Maybe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

import static com.s.karpardaz.base.di.NetworkUtil.isResponseValid;
import static com.s.karpardaz.base.util.AppUtil.produceNetworkException;
import static java.util.Objects.requireNonNull;

@EverythingIsNonNull
public class UserRemoteDataSource implements UserDataSource {

    private final UserService mUserService;

    @Inject
    UserRemoteDataSource(@NonNull UserService service) {
        mUserService = requireNonNull(service);
    }

    @Override
    public Maybe<Login> getLoggedInUser() {
        throw new NotImplementedException();
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
        throw new NotImplementedException();
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
    public void login(@NonNull String loginPhrase, @NonNull LoginCallback callback) {
        requireNonNull(loginPhrase);
        requireNonNull(callback);

        mUserService.login(loginPhrase).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (isResponseValid(response)) callback.onSuccess(response.body());
                else if (response.code() == HttpURLConnection.HTTP_NOT_FOUND)
                    callback.informationNotFound();
                else
                    callback.onFailure(new Throwable("خطای ناشناخته:".concat(String.valueOf(response.code()))));
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    @Override
    public void register(@NonNull User user, @NonNull RegisterCallback callback) {
        requireNonNull(user);
        requireNonNull(callback);
        mUserService.register(user).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (isResponseValid(response))
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
