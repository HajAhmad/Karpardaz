package com.s.karpardaz.user;

import androidx.annotation.NonNull;

import com.s.karpardaz.R;
import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.base.BasePresenter;
import com.s.karpardaz.base.util.AppUtil;
import com.s.karpardaz.user.data.LoginDataSource;

import java.util.Objects;

import javax.inject.Inject;

public class RecoveryPasswordPresenter extends BasePresenter<RecoveryPasswordContract.View>
    implements RecoveryPasswordContract.Presenter {

    private final LoginDataSource mRepository;

    @Inject
    RecoveryPasswordPresenter(@NonNull LoginDataSource repository) {
        mRepository = Objects.requireNonNull(repository);
    }

    @Override
    public void start() {
        getView().initEmailView();
    }

    @Override
    public void recoverPassword(String email) {
        if (AppUtil.isEmailInvalid(email)) {
            getView().showInvalidEmailError();
        } else {
            getView().showProgress(R.string.recover_password_progress_message);
            mRepository.RequestPasswordChange(email, new BaseCallback<Void>() {
                @Override
                public void onSuccess(Void result) {
                    getView().hideProgress();
                    getView().initCodeView();
                }

                @Override
                public void onFailure(Throwable t) {
                    getView().showMessage(t.getMessage());
                    getView().hideProgress();
                }
            });
        }
    }


}
