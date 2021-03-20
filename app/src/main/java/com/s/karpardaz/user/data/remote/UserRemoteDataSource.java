package com.s.karpardaz.user.data.remote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.user.data.UserDataSource;
import com.s.karpardaz.user.model.Login;
import com.s.karpardaz.user.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

import static com.s.karpardaz.base.di.remote.NetworkUtil.isAuthenticationError;
import static com.s.karpardaz.base.di.remote.NetworkUtil.isResponseValid;
import static java.util.Objects.requireNonNull;

@EverythingIsNonNull
public class UserRemoteDataSource implements UserDataSource {

    private final UserService mUserService;

    public UserRemoteDataSource(@NonNull UserService service) {
        mUserService = requireNonNull(service);
    }

    @Override
    public void getLoggedInUser() {

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
    public LiveData<String> register(@Nullable String name, @NonNull String email, @NonNull String password,
            @NonNull String currentDateTime) {
        final MutableLiveData<String> liveData = new MutableLiveData<>();
        mUserService.register(new User(0, "", name, email, password,
                currentDateTime, currentDateTime)).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (isAuthenticationError(response.code())){
                } else if (isResponseValid(response)){

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        return null;
    }



}
