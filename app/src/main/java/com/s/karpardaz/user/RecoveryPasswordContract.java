package com.s.karpardaz.user;

import androidx.annotation.StringRes;

import com.s.karpardaz.base.BasePresenterContract;
import com.s.karpardaz.base.BaseViewContract;

public interface RecoveryPasswordContract {
    interface View extends BaseViewContract<Presenter> {
        void initEmailView();
        void initCodeView();
        void showInvalidPasswordError();
        void showInvalidEmailError();
        void showMessage(@StringRes int stringResId);
        void showMessage(String message);
        void startRecoveryCodeCountDownTimer();
        void stopTimer();
        void initResendCode();
        void enableSendCodeAction();
        void disableSendCodeAction();
        void initChangePassword();
        void codeNotFound();
        void codeExpired();
        void disableResetPasswordAction();
        void enableResetPasswordAction();
        void dismissDialog();
    }

    interface Presenter extends BasePresenterContract<View> {
        void requestRecoveryCode(String email);
        void resendPasswordRecoveryCode();
        void startRecoveryCodeCountDownTimer();
        void initResendCode();
        void sendRecoveryCode(String verificationCode);
        void enableSendCodeAction();
        void disableSendCodeAction();
        void enableResetPasswordAction();
        void disableResetPasswordAction();
        void resetPassword(String password);
    }
}
