package com.s.karpardaz.user.data;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.base.NotImplementedException;
import com.s.karpardaz.user.model.User;

public interface UserDataSource {

    default void getUser(@NonNull final String uuid){
        throw new NotImplementedException();
    }
    default void getUser(@NonNull final Long id){
        throw new NotImplementedException();
    }
    default void getUser(@NonNull final String email, @NonNull final String password){
        throw new NotImplementedException();
    }
    default void insertUser(@NonNull final User user, @NonNull final BaseCallback<String> callback){
        throw new NotImplementedException();
    }
    default void deleteUser(@NonNull final User user){
        throw new NotImplementedException();
    }
    default void register(@NonNull User user, @NonNull RegisterCallback callback){
        throw new NotImplementedException();
    }

    interface RegisterCallback extends BaseCallback<String> {
        void userExists();
    }

}
