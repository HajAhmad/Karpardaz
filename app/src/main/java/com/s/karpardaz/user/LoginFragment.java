package com.s.karpardaz.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.s.karpardaz.R;
import com.s.karpardaz.base.ui.BaseBindingFragment;
import com.s.karpardaz.base.util.view.SnackbarUtil;
import com.s.karpardaz.databinding.LayoutLoginBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

import static com.s.karpardaz.base.util.view.SnackbarUtil.showSnackbar;

@AndroidEntryPoint
public class LoginFragment extends BaseBindingFragment<OnLoginInteractionListener,
        LayoutLoginBinding> implements LoginContract.View {

    public static final String TAG = LoginFragment.class.getSimpleName();

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

    private void recoverPassword() {
        final View dialogView = LayoutInflater.from(getCtx())
                .inflate(R.layout.layout_password_recovery, null, false);
        new AlertDialog.Builder(getCtx())
                .setView(dialogView).create().show();
        dialogView.findViewById(R.id.layout_password_recovery_action).setOnClickListener(v -> {
            dialogView.findViewById(R.id.layout_password_recovery_input);
            mPresenter.recoverPassword();
        });
    }

    private void login() {
        if (getBinding().layoutLoginEmailInput.getText() != null &&
                getBinding().layoutLoginPasswordInput.getText() != null) {
            String email = getBinding().layoutLoginEmailInput.getText().toString();
            String password = getBinding().layoutLoginPasswordInput.getText().toString();
            mPresenter.login(email, password);

        } else {
            showSnackbar(getBinding().getRoot(), R.string.all_email_or_password_is_empty);
        }
    }


    @Override
    public void showInvalidEmailError() {
        SnackbarUtil.showSnackbar(getBinding().getRoot(), R.string.all_invalid_user_information_message);
    }

    @Override
    public void showInvalidPasswordError() {
        getBinding().layoutLoginPasswordInput.setText("");
    }

    @Override
    public void showProgress() {

    }
}

