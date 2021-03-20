package com.s.karpardaz.user.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.user.model.Login;
import com.s.karpardaz.user.model.User;

public interface UserDataSource {

    void getLoggedInUser();

    void getUser(@NonNull final String uuid);

    void getUser(@NonNull final Long id);

    void getUser(@NonNull final String email, @NonNull final String password);

    void insertUser(@NonNull final User user, @NonNull final BaseCallback<String> callback);

    void insertLogin(@NonNull final Login login, @NonNull final BaseCallback<String> callback);

    void deleteUser(@NonNull final User user);

    void deleteLogin(@NonNull final Login login);

    void login(@NonNull String email, @NonNull String password);

    LiveData<String> register(@Nullable String name,
            @NonNull String email,
            @NonNull String password,
            @NonNull String currentDateTime);

}
