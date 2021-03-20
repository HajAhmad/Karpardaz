package com.s.karpardaz.user;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.s.karpardaz.R;
import com.s.karpardaz.base.ui.BaseBindingFragment;
import com.s.karpardaz.base.util.view.SnackbarUtil;
import com.s.karpardaz.databinding.FragmentRegisterBinding;
import com.s.karpardaz.main.MainActivity;

public class RegisterFragment extends BaseBindingFragment<OnRegisterInteractionListener, FragmentRegisterBinding> {

    public static final String TAG = RegisterFragment.class.getSimpleName();

    private UserViewModel mVm;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
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
        setBinding(FragmentRegisterBinding.inflate(inflater, container, false));
        return getBinding().getRoot();
    }

    @Override
    public void onViewCreated(@Nullable Bundle savedInstanceState) {
        getBinding().fragmentRegisterPolicyUsageRulesText.setLinkTextColor(
                ContextCompat.getColor(getCtx(), R.color.colorPrimary));
        getBinding().fragmentRegisterPolicyUsageRulesText.setMovementMethod(LinkMovementMethod.getInstance());

        getBinding().fragmentRegisterRegisterAction.setOnClickListener(v -> register());

        observeLoading();
    }

    private void observeLoading() {
        mVm.getLoadingLive().observe(this, isLoading -> {
            if (isLoading) showProgress();
            else hideProgress();
        });
    }

    private void register() {
        if (getBinding().fragmentRegisterEmailInput.getText() != null &&
                getBinding().fragmentRegisterPasswordInput.getText() != null) {
            mVm.register(getBinding().fragmentRegisterNameInput.getText().toString(),
                    getBinding().fragmentRegisterEmailInput.getText().toString(),
                    getBinding().fragmentRegisterPasswordInput.getText().toString());
        } else {
            SnackbarUtil.showSnackbar(getBinding().getRoot(), R.string.all_check_value_message);
        }
    }

}
