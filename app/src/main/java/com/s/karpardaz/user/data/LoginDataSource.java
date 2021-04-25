package com.s.karpardaz.user.data;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.base.NotImplementedException;
import com.s.karpardaz.user.model.Login;

import io.reactivex.rxjava3.core.Maybe;

public interface LoginDataSource {

    default Maybe<Login> getLoggedInUser() {
        throw new NotImplementedException();
    }
    default void insertLogin(@NonNull Login login, @NonNull BaseCallback<Void> callback) {
        throw new NotImplementedException();
    }
    default void deleteLogin(@NonNull Login login) {
        throw new NotImplementedException();
    }
    default void login(@NonNull String loginPhrase, @NonNull LoginCallback loginCallback) {
        throw new NotImplementedException();
    }
    void RequestPasswordChange(@NonNull String email, @NonNull BaseCallback<Void> callback);

    interface LoginCallback extends BaseCallback<String> {
        void informationNotFound();
    }

}
