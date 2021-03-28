package com.s.karpardaz.user;

import androidx.annotation.NonNull;

import com.s.karpardaz.R;
import com.s.karpardaz.base.BasePresenter;
import com.s.karpardaz.base.util.AppUtil;
import com.s.karpardaz.user.data.UserDataSource;
import com.s.karpardaz.user.model.Login;

import java.util.Objects;

import javax.inject.Inject;

import static com.s.karpardaz.base.util.AppUtil.composeLoginPhrase;
import static com.s.karpardaz.base.util.AppUtil.getCurrentDateTimeUTC;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    private final UserDataSource mRepository;

    @Inject
    LoginPresenter(@NonNull UserDataSource repository) {
        mRepository = Objects.requireNonNull(repository);
    }

    @Override
    public void login(String email, String password) {
        if (AppUtil.isPasswordInvalid(password)) {
            getView().showInvalidPasswordError();
        } else if (AppUtil.isEmailInvalid(email)) {
            getView().showInvalidEmailError();
        } else {
            getView().showProgress();
            mRepository.login(composeLoginPhrase(email, password), new UserDataSource.LoginCallback() {
                @Override
                public void informationNotFound() {
                    //TODO
                }

                @Override
                public void onSuccess(String userId) {
                    //TODO
                    mRepository.insertLogin(new Login(userId, getCurrentDateTimeUTC()),
                            result -> {

                            });
                }

                @Override
                public void onFailure(Throwable t) {
                    //TODO
                }
            });
        }
    }

    @Override
    public void recoverPassword() {
        getView().showProgress(R.string.recover_password_progress_message);

    }


}
