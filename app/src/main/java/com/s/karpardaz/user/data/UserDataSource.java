package com.s.karpardaz.user.data;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.user.model.Login;
import com.s.karpardaz.user.model.User;

import io.reactivex.rxjava3.core.Maybe;

public interface UserDataSource {

    Maybe<Login> getLoggedInUser();

    void getUser(@NonNull final String uuid);

    void getUser(@NonNull final Long id);

    void getUser(@NonNull final String email, @NonNull final String password);

    void insertUser(@NonNull final User user, @NonNull final BaseCallback<String> callback);

    void insertLogin(@NonNull final Login login, @NonNull final BaseCallback<String> callback);

    void deleteUser(@NonNull final User user);

    void deleteLogin(@NonNull final Login login);

    void login(@NonNull final String loginPhrase,
            @NonNull final LoginCallback loginCallback);

    void register(@NonNull User user, @NonNull RegisterCallback callback);

    interface RegisterCallback extends BaseCallback<String> {
        void userExists();
    }

    interface LoginCallback extends BaseCallback<String> {
        void informationNotFound();
    }
}
