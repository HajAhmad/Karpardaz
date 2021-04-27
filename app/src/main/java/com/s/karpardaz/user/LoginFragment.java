package com.s.karpardaz.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.s.karpardaz.R;
import com.s.karpardaz.base.ui.BaseFragment;
import com.s.karpardaz.databinding.LayoutLoginBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends BaseFragment<OnLoginInteractionListener, LayoutLoginBinding>
    implements LoginContract.View,
    RecoveryPasswordFragment.OnRecoveryPasswordInteractionListener {

    public static final String TAG = LoginFragment.class.getSimpleName();
    private AlertDialog mPasswordRecoveryDialog;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Inject
    LoginContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
        @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        setBinding(LayoutLoginBinding.inflate(inflater, container, false));
        return getBinding().getRoot();
    }

    @Override
    public void onViewCreated(@Nullable Bundle savedInstanceState) {
        getBinding().layoutLoginPasswordRecoveryAction.setOnClickListener(v -> recoverPassword());
        getBinding().layoutLoginLoginAction.setOnClickListener(v -> login());

    }

    @Override
    protected void clearReferences() {
        super.clearReferences();
        mPresenter.dropView();
        mPresenter = null;
    }

    private void recoverPassword() {
        RecoveryPasswordFragment fragment = RecoveryPasswordFragment.newInstance();
        fragment.setInteractionListener(this);

    }

    private void login() {
        if (getBinding().layoutLoginEmailInput.getText() != null &&
            getBinding().layoutLoginPasswordInput.getText() != null) {
            String email = getBinding().layoutLoginEmailInput.getText().toString();
            String password = getBinding().layoutLoginPasswordInput.getText().toString();
            mPresenter.login(email, password);

        } else {
            showMessage(R.string.all_email_or_password_is_empty);
        }
    }

    @Override
    public void showInvalidEmailError() {
        showMessage(R.string.all_invalid_user_information_message);
    }

    @Override
    public void showInvalidPasswordError() {
        getBinding().layoutLoginPasswordInput.setText(R.string.entry_invalid_password_message);
    }

    @Override
    public void proceed() {
        getListener().proceed();
    }

    @Override
    public void showInfoNotFoundError() {
        showMessage(R.string.all_invalid_user_information_message);
    }

    @Override
    public void showMessage(int stringResId) {
        getListener().showMessage(stringResId);
    }

    @Override
    public void showMessage(String message) {
        getListener().showMessage(message);
    }

    @Override
    public void showPasswordRecoverySucceed() {
        showMessage(R.string.password_recovery_success_message);
        mPasswordRecoveryDialog.dismiss();
    }

}

