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
import com.s.karpardaz.base.ui.BaseFragment;
import com.s.karpardaz.databinding.FragmentRegisterBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

import static com.s.karpardaz.base.util.view.AlertUtil.showLongToast;
import static com.s.karpardaz.base.util.view.AlertUtil.showToast;

@AndroidEntryPoint
public class RegisterFragment extends BaseFragment<OnRegisterInteractionListener, FragmentRegisterBinding>
    implements RegisterContract.View {

    public static final String TAG = RegisterFragment.class.getSimpleName();

    @Inject
    RegisterContract.Presenter mPresenter;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.takeView(this);
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
    }


    private void register() {
        if (getBinding().fragmentRegisterEmailInput.getText() != null &&
            getBinding().fragmentRegisterPasswordInput.getText() != null) {

            mPresenter.register(getBinding().fragmentRegisterNameInput.getText().toString(),
                getBinding().fragmentRegisterEmailInput.getText().toString(),
                getBinding().fragmentRegisterPasswordInput.getText().toString());
        } else {
            showToast(getRoot(), R.string.all_check_value_message);
        }
    }

    @Override
    protected void clearReferences() {
        super.clearReferences();
        mPresenter.dropView();
        mPresenter = null;
    }

    @Override
    public void showInvalidPasswordError() {
        showToast(getBinding().getRoot(), R.string.long_invalid_password_message);
    }

    @Override
    public void showInvalidEmailError() {
        showLongToast(getBinding().getRoot(), R.string.entry_invalid_email_message);
    }

    @Override
    public void emailFound() {
        showToast(getBinding().getRoot(), R.string.register_email_found_error_message);
    }

    @Override
    public void showThrowable(Throwable t) {
        showToast(getRoot(), t.getMessage());
    }

    @Override
    public void networkUnavailable() {
        showToast(getBinding().getRoot(), R.string.all_check_network_message);
    }

    @Override
    public void proceed() {
        getListener().proceed();
    }

    @Override
    public void notFound() {
        showToast(getBinding().getRoot(), R.string.info_not_found);
    }

}
