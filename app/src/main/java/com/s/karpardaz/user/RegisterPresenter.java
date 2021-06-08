package com.s.karpardaz.user;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.base.BasePresenter;
import com.s.karpardaz.base.util.AppConstants;
import com.s.karpardaz.base.util.AppUtil;
import com.s.karpardaz.user.data.LoginDataSource;
import com.s.karpardaz.user.data.UserDataSource;
import com.s.karpardaz.base.model.Login;
import com.s.karpardaz.base.model.User;

import javax.inject.Inject;

import static com.s.karpardaz.KarpardazApplication.getInstance;
import static com.s.karpardaz.base.util.AppUtil.composeLoginPhrase;
import static com.s.karpardaz.base.util.AppUtil.getCurrentDateTimeUTC;
import static java.util.Objects.requireNonNull;

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements
    RegisterContract.Presenter {

    private final UserDataSource mUserRepository;
    private final LoginDataSource mLoginRepository;

    @Inject
    RegisterPresenter(@NonNull UserDataSource repository,
        @NonNull LoginDataSource loginRepository) {
        mUserRepository = requireNonNull(repository);
        mLoginRepository = requireNonNull(loginRepository);
    }

    @Override
    public void register(@Nullable String name, String email, String password) {
        if (AppUtil.isEmailInvalid(email)) {
            getView().showInvalidEmailError();
        } else if (AppUtil.isPasswordInvalid(password)) {
            getView().showInvalidPasswordError();
        } else {
            if (AppUtil.inNetworkAvailable(getInstance())) {
                if (TextUtils.isEmpty(name))
                    name = email.split("@")[0];

                password = composeLoginPhrase(email, password);
                User user = new User(name, email, password, getCurrentDateTimeUTC());

                getView().showProgress();
                mUserRepository.register(user, new UserDataSource.RegisterCallback() {
                    @Override
                    public void onSuccess(String userId) {
                        mLoginRepository.insertLogin(new Login(userId, getCurrentDateTimeUTC()),
                            new BaseCallback<Void>() {
                                @Override
                                public void onSuccess(Void result) {
                                    getView().proceed();
                                    AppConstants.sActiveUserId = userId;
                                    getView().hideProgress();
                                }

                                @Override
                                public void onFailure(Throwable t) {
                                    getView().showThrowable(t);
                                    getView().hideProgress();
                                }
                            });
                    }

                    @Override
                    public void userExists() {
                        getView().emailFound();
                        getView().hideProgress();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        getView().showThrowable(t);
                        getView().hideProgress();
                    }
                });
            } else {
                getView().networkUnavailable();
            }
        }
    }

}
