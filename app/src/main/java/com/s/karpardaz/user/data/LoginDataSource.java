package com.s.karpardaz.user.data;

import androidx.annotation.NonNull;

import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.base.NotImplementedException;
import com.s.karpardaz.base.model.BaseResponse;
import com.s.karpardaz.base.model.Login;

import javax.annotation.Nonnull;

public interface LoginDataSource {

    default void getLoggedInUser(@Nonnull GetLoggedInUserCallback callback) {
        throw new NotImplementedException();
    }

    default void login(@NonNull String email, @NonNull String password, @NonNull String createdAt, @NonNull LoginCallback loginCallback) {
        throw new NotImplementedException();
    }
    void RequestPasswordChange(@NonNull String email, @NonNull BaseCallback<String> callback);
    void sendRecoveryCode(@NonNull String sRecoveryToken, @NonNull String verificationCode,
        @NonNull SendRecoveryCodeCallback callback);
    void resetPassword(@NonNull String recoveryToken, @NonNull Login login, @NonNull ResetPasswordCallback callback);
    void clearLoginInfo(@NonNull BaseCallback<Void> callback);

    interface SendRecoveryCodeCallback extends BaseCallback<BaseResponse<String>> {
        void notFound();
        void codeExpired();
    }

    interface GetLoggedInUserCallback extends BaseCallback<Login>{
        void notFound();
    }

    interface LoginCallback extends BaseCallback<String> {
        void informationNotFound();
    }

    interface ResetPasswordCallback extends BaseCallback<Void>{

        void notFound();
    }

}
