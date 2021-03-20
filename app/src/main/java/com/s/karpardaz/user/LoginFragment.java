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
import com.s.karpardaz.databinding.LayoutLoginBinding;
import com.s.karpardaz.main.MainActivity;

import static com.s.karpardaz.base.util.view.SnackbarUtil.showSnackbar;

public class LoginFragment extends BaseBindingFragment<OnLoginInteractionListener,
        LayoutLoginBinding> {

    private UserViewModel mVm;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVm = MainActivity.obtainUserViewModel(getActivity());
    }

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
        final View rootView = LayoutInflater.from(getCtx())
                .inflate(R.layout.layout_password_recovery, null, false);
        new AlertDialog.Builder(getCtx())
                .setView(rootView).create().show();
    }

    private void login() {
        if (getBinding().layoutLoginEmailInput.getText() != null &&
                getBinding().layoutLoginPasswordInput.getText() != null) {
            if (mVm.isEmailInvalid(getBinding().layoutLoginEmailInput.getText().toString())) {
                showSnackbar(getBinding().getRoot(), R.string.entry_invalid_email_message);
            } else if (mVm.isPasswordInvalid(getBinding().layoutLoginPasswordInput.getText().toString())) {
                showSnackbar(getBinding().getRoot(), R.string.long_invalid_password_message);
            } else {
                mVm.login(getBinding().layoutLoginEmailInput.getText().toString(),
                        getBinding().layoutLoginPasswordInput.getText().toString()
                );
            }
        } else {
            showSnackbar(getBinding().getRoot(), R.string.all_email_or_password_is_empty);
        }
    }

}

