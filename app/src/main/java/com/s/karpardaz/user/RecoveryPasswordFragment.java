package com.s.karpardaz.user;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;

import com.s.karpardaz.R;
import com.s.karpardaz.base.KTextWatcher;
import com.s.karpardaz.base.ui.BaseDialogFragment;
import com.s.karpardaz.base.ui.BaseInteractionListener;
import com.s.karpardaz.databinding.LayoutPasswordRecoveryBinding;
import com.s.karpardaz.databinding.LayoutPasswordRecoveryStepOneBinding;
import com.s.karpardaz.databinding.LayoutPasswordRecoveryStepThreeBinding;
import com.s.karpardaz.databinding.LayoutPasswordRecoveryStepTwoBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RecoveryPasswordFragment extends BaseDialogFragment<RecoveryPasswordFragment.OnRecoveryPasswordInteractionListener,
    LayoutPasswordRecoveryBinding> implements RecoveryPasswordContract.View {

    public static final String TAG = RecoveryPasswordFragment.class.getSimpleName();

    @Inject
    RecoveryPasswordContract.Presenter mPresenter;

    public static final int EMAIL_VIEW_POSITION = 0;
    public static final int CODE_VIEW_POSITION = 1;
    public static final int CHANGE_PASSWORD_VIEW_POSITION = 2;

    private LayoutPasswordRecoveryStepOneBinding mEmailViewBinding;
    private LayoutPasswordRecoveryStepTwoBinding mCodeViewBinding;
    private LayoutPasswordRecoveryStepThreeBinding mChangePasswordBinding;

    private CountDownTimer recoveryCodeRemainingCounter;

    public static RecoveryPasswordFragment newInstance() {
        return new RecoveryPasswordFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.takeView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        setBinding(LayoutPasswordRecoveryBinding.inflate(inflater, container, false));
        return getRoot();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        mEmailViewBinding = LayoutPasswordRecoveryStepOneBinding.inflate(
            LayoutInflater.from(getCtx()), null, false);
        mCodeViewBinding = LayoutPasswordRecoveryStepTwoBinding.inflate(
            LayoutInflater.from(getCtx()), null, false);
        mChangePasswordBinding = LayoutPasswordRecoveryStepThreeBinding.inflate(
            LayoutInflater.from(getCtx()), null, false);

        ViewPagerAdapter adapter = new ViewPagerAdapter();
        adapter.addView(EMAIL_VIEW_POSITION, mEmailViewBinding.getRoot());
        adapter.addView(CODE_VIEW_POSITION, mCodeViewBinding.getRoot());
        adapter.addView(CHANGE_PASSWORD_VIEW_POSITION, mChangePasswordBinding.getRoot());

        getBinding().layoutLoginPasswordRecoveryPager.setOnTouchListener((v, event) -> false);
        getBinding().layoutLoginPasswordRecoveryPager.setAdapter(adapter);

        mPresenter.start();
    }

    @Override
    public void initEmailView() {
        mEmailViewBinding.layoutPasswordRecoveryAction.setOnClickListener(v -> {
            if (mEmailViewBinding.layoutPasswordRecoveryInput.getText() != null)
                mPresenter.requestRecoveryCode(
                    mEmailViewBinding.layoutPasswordRecoveryInput.getText().toString());
            else
                getListener().showMessage(R.string.all_enter_email_message);
        });
        getBinding().layoutLoginPasswordRecoveryPager.setCurrentItem(EMAIL_VIEW_POSITION);
    }


    @Override
    public void initCodeView() {
        mCodeViewBinding.layoutPasswordRecoveryInput.addTextChangedListener((KTextWatcher) (s, count) -> {
            if (s.length() == 4) mPresenter.enableSendCodeAction();
            else mPresenter.disableSendCodeAction();
        });

        mCodeViewBinding.layoutPasswordRecoveryAction.setOnClickListener(v ->
            mPresenter.sendRecoveryCode(mCodeViewBinding.layoutPasswordRecoveryInput.getText().toString()));


        getBinding().layoutLoginPasswordRecoveryPager.setCurrentItem(CODE_VIEW_POSITION);

        mPresenter.startRecoveryCodeCountDownTimer();
        mPresenter.disableSendCodeAction();
    }

    @Override
    public void initChangePassword() {

        mChangePasswordBinding.layoutPasswordRecoveryInput.addTextChangedListener((KTextWatcher) (s, count) -> {
            if (count > 4) mPresenter.enableResetPasswordAction();
            else mPresenter.disableResetPasswordAction();
        });
        mChangePasswordBinding.layoutPasswordRecoveryAction.setOnClickListener(v ->
            mPresenter.resetPassword(mChangePasswordBinding.layoutPasswordRecoveryInput.getText().toString()));

        getBinding().layoutLoginPasswordRecoveryPager.setCurrentItem(CHANGE_PASSWORD_VIEW_POSITION);

        mPresenter.disableResetPasswordAction();
    }

    @Override
    public void startRecoveryCodeCountDownTimer() {
        mCodeViewBinding.layoutPasswordRecoveryRemainingTime.setClickable(false);
        mCodeViewBinding.layoutPasswordRecoveryRemainingTime.setFocusable(false);

        recoveryCodeRemainingCounter = new CountDownTimer(TimeUnit.MINUTES.toMillis(2),
            TimeUnit.SECONDS.toMillis(1)) {
            @Override
            public void onTick(long millisUntilFinished) {
                long minutes = TimeUnit.MINUTES.convert(millisUntilFinished, TimeUnit.MILLISECONDS);
                long seconds = TimeUnit.SECONDS.convert(millisUntilFinished, TimeUnit.MILLISECONDS);

                mCodeViewBinding.layoutPasswordRecoveryRemainingTime.setText(
                    String.format("%d:%d", minutes, seconds));
            }

            @Override
            public void onFinish() {
                mPresenter.initResendCode();
            }
        };

        recoveryCodeRemainingCounter.start();
    }

    @Override
    public void stopTimer() {
        if (recoveryCodeRemainingCounter != null)
            recoveryCodeRemainingCounter.cancel();
    }

    @Override
    public void initResendCode() {
        mCodeViewBinding.layoutPasswordRecoveryRemainingTime.setText(
            R.string.recovery_code_expired_message);
        mCodeViewBinding.layoutPasswordRecoveryRemainingTime.setTextColor(
            ContextCompat.getColor(getCtx(), R.color.colorLink));
        mCodeViewBinding.layoutPasswordRecoveryAction.setOnClickListener(v ->
            mPresenter.resendPasswordRecoveryCode());
        mCodeViewBinding.layoutPasswordRecoveryAction.setFocusable(true);
    }

    @Override
    public void enableSendCodeAction() {
        mCodeViewBinding.layoutPasswordRecoveryAction.setEnabled(true);
        mCodeViewBinding.layoutPasswordRecoveryAction.setTextColor(
            ContextCompat.getColor(getCtx(), R.color.colorPrimary));
    }

    @Override
    public void disableSendCodeAction() {
        mCodeViewBinding.layoutPasswordRecoveryAction.setEnabled(false);
        mCodeViewBinding.layoutPasswordRecoveryAction.setTextColor(
            ContextCompat.getColor(getCtx(), R.color.colorHint));
    }

    @Override
    public void codeNotFound() {
        showMessage(R.string.verification_code_not_found);
        mCodeViewBinding.layoutPasswordRecoveryInput.setText("");
    }

    @Override
    public void codeExpired() {
        showMessage(R.string.verification_code_expired);
        mCodeViewBinding.layoutPasswordRecoveryInput.setText("");
    }

    @Override
    public void disableResetPasswordAction() {
        mChangePasswordBinding.layoutPasswordRecoveryAction.setEnabled(false);
        mChangePasswordBinding.layoutPasswordRecoveryAction.setTextColor(
            ContextCompat.getColor(getCtx(), R.color.colorHint)
        );
    }

    @Override
    public void enableResetPasswordAction() {
        mChangePasswordBinding.layoutPasswordRecoveryAction.setEnabled(true);
        mChangePasswordBinding.layoutPasswordRecoveryAction.setTextColor(
            ContextCompat.getColor(getCtx(), R.color.colorPrimary)
        );
    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void showInvalidPasswordError() {
        showMessage(R.string.long_invalid_password_message);
    }

    @Override
    public void showInvalidEmailError() {
        showMessage(R.string.entry_invalid_email_message);
    }

    @Override
    protected void clearReferences() {
        super.clearReferences();
        if (recoveryCodeRemainingCounter != null) recoveryCodeRemainingCounter.cancel();
        mPresenter.dropView();
        mPresenter = null;
    }

    private static class ViewPagerAdapter extends PagerAdapter {
        private final List<View> views;

        ViewPagerAdapter() {
            views = new ArrayList<>(3);
            notifyDataSetChanged();
        }

        void addView(int position, View view) {
            if (position > 2)
                throw new IndexOutOfBoundsException();

            views.add(position, view);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position,
            @NonNull Object object) {
            container.removeView((View) object);
        }
    }

    public interface OnRecoveryPasswordInteractionListener extends BaseInteractionListener {

    }
}
