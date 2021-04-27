package com.s.karpardaz.user;

import androidx.annotation.NonNull;

import com.s.karpardaz.R;
import com.s.karpardaz.base.BaseCallback;
import com.s.karpardaz.base.BasePresenter;
import com.s.karpardaz.base.model.BaseResponse;
import com.s.karpardaz.base.util.AppUtil;
import com.s.karpardaz.user.data.LoginDataSource;

import java.util.Objects;

import javax.inject.Inject;

import static com.s.karpardaz.base.util.AppUtil.isAnyEmpty;

public class RecoveryPasswordPresenter extends BasePresenter<RecoveryPasswordContract.View>
    implements RecoveryPasswordContract.Presenter {

    private final LoginDataSource mRepository;

    private static String sRecoveryToken;
    private static String sUserEmail;

    @Inject
    RecoveryPasswordPresenter(@NonNull LoginDataSource repository) {
        mRepository = Objects.requireNonNull(repository);
    }

    @Override
    public void start() {
        getView().initEmailView();
    }

    @Override
    public void requestRecoveryCode(String email) {
        if (AppUtil.isEmailInvalid(email)) {
            getView().showInvalidEmailError();
        } else {
            sUserEmail = email;
            getView().showProgress(R.string.recover_password_progress_message);
            mRepository.RequestPasswordChange(email, new BaseCallback<String>() {
                @Override
                public void onSuccess(String recoveryToken) {
                    sRecoveryToken = recoveryToken;
                    getView().hideProgress();
                    getView().initCodeView();
                }

                @Override
                public void onFailure(Throwable t) {
                    getView().initResendCode();
                    getView().showMessage(t.getMessage());
                    getView().hideProgress();
                }
            });
        }
    }

    @Override
    public void resendPasswordRecoveryCode() {
        mRepository.RequestPasswordChange(sUserEmail, new BaseCallback<String>() {
            @Override
            public void onSuccess(String recoveryToken) {
                sRecoveryToken = recoveryToken;
                getView().hideProgress();
                getView().initCodeView();
            }

            @Override
            public void onFailure(Throwable t) {
                getView().initResendCode();
                getView().showMessage(t.getMessage());
                getView().hideProgress();
            }
        });
    }

    @Override
    public void startRecoveryCodeCountDownTimer() {
        getView().startRecoveryCodeCountDownTimer();
    }

    @Override
    public void initResendCode() {
        getView().initResendCode();
    }

    @Override
    public void sendRecoveryCode(String verificationCode) {
        if (isAnyEmpty(verificationCode)) {
            getView().showInvalidCodeError();
        } else {
            getView().showProgress();
            mRepository.sendRecoveryCode(sRecoveryToken, verificationCode,
                new LoginDataSource.SendRecoveryCodeCallback() {
                    @Override
                    public void notFound() {

                    }

                    @Override
                    public void codeExpired() {

                    }

                    @Override
                    public void onSuccess(BaseResponse<String> result) {

                    }
                });
        }
    }

    @Override
    public void enableSendCodeAction() {
        getView().enableSendCodeAction();
    }

    @Override
    public void disableSendCodeAction() {
        getView().disableSendCodeAction();
    }


}
