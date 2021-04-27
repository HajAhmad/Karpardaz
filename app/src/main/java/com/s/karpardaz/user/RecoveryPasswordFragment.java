package com.s.karpardaz.user;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;

import com.s.karpardaz.R;
import com.s.karpardaz.base.NotImplementedException;
import com.s.karpardaz.base.ui.BaseFragment;
import com.s.karpardaz.base.ui.BaseInteractionListener;
import com.s.karpardaz.databinding.LayoutPasswordRecoveryBinding;
import com.s.karpardaz.databinding.LayoutPasswordRecoveryStepOneBinding;
import com.s.karpardaz.databinding.LayoutPasswordRecoveryStepTwoBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RecoveryPasswordFragment extends BaseFragment<RecoveryPasswordFragment.OnRecoveryPasswordInteractionListener,
    LayoutPasswordRecoveryBinding> implements RecoveryPasswordContract.View {

    @Inject
    RecoveryPasswordContract.Presenter mPresenter;

    private ViewPagerAdapter mAdapter;
    private LayoutPasswordRecoveryStepTwoBinding stepTwoBinding;
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
        mAdapter = new ViewPagerAdapter();
        getBinding().layoutLoginPasswordRecoveryPager.setOnTouchListener((v, event) -> false);
        getBinding().layoutLoginPasswordRecoveryPager.setAdapter(mAdapter);

        mPresenter.start();
    }

    @Override
    public void initEmailView() {
        LayoutPasswordRecoveryStepOneBinding binding = LayoutPasswordRecoveryStepOneBinding.inflate(
            LayoutInflater.from(getCtx()), null, false
        );
        binding.layoutPasswordRecoveryAction.setOnClickListener(v -> {
            if (binding.layoutPasswordRecoveryInput.getText() != null)
                mPresenter.requestRecoveryCode(
                    binding.layoutPasswordRecoveryInput.getText().toString());
            else
                getListener().showMessage(R.string.all_enter_email_message);
        });
        mAdapter.addView(binding.getRoot());
        getBinding().layoutLoginPasswordRecoveryPager.setCurrentItem(0);
    }


    @Override
    public void initCodeView() {
        stepTwoBinding = LayoutPasswordRecoveryStepTwoBinding.inflate(
            LayoutInflater.from(getCtx()), null, false
        );

        mPresenter.startRecoveryCodeCountDownTimer();
        mPresenter.disableSendCodeAction();
        stepTwoBinding.layoutPasswordRecoveryInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 4) mPresenter.enableSendCodeAction();
                else mPresenter.disableSendCodeAction();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        stepTwoBinding.layoutPasswordRecoveryAction.setOnClickListener(v -> {
            mPresenter.sendRecoveryCode(stepTwoBinding.layoutPasswordRecoveryInput.getText().toString());

            recoveryCodeRemainingCounter.cancel();
        });

        mAdapter.addView(stepTwoBinding.getRoot());
        getBinding().layoutLoginPasswordRecoveryPager.setCurrentItem(1);
    }

    @Override
    public void startRecoveryCodeCountDownTimer() {
        stepTwoBinding.layoutPasswordRecoveryRemainingTime.setClickable(false);
        stepTwoBinding.layoutPasswordRecoveryRemainingTime.setFocusable(false);

        recoveryCodeRemainingCounter = new CountDownTimer(TimeUnit.MINUTES.toMillis(2),
            TimeUnit.SECONDS.toMillis(1)) {
            @Override
            public void onTick(long millisUntilFinished) {
                long minutes = TimeUnit.MINUTES.convert(millisUntilFinished, TimeUnit.MILLISECONDS);
                long seconds = TimeUnit.SECONDS.convert(millisUntilFinished, TimeUnit.MILLISECONDS);

                stepTwoBinding.layoutPasswordRecoveryRemainingTime.setText(
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
    public void initResendCode() {
        stepTwoBinding.layoutPasswordRecoveryRemainingTime.setText(
            R.string.recovery_code_expired_message);
        stepTwoBinding.layoutPasswordRecoveryRemainingTime.setTextColor(
            ContextCompat.getColor(getCtx(), R.color.colorLink));
        stepTwoBinding.layoutPasswordRecoveryAction.setOnClickListener(v ->
            mPresenter.resendPasswordRecoveryCode());
        stepTwoBinding.layoutPasswordRecoveryAction.setFocusable(true);
    }

    @Override
    public void enableSendCodeAction() {
        stepTwoBinding.layoutPasswordRecoveryAction.setEnabled(true);
        stepTwoBinding.layoutPasswordRecoveryAction.setTextColor(ContextCompat.getColor(getCtx(), R.color.colorPrimary));
    }

    @Override
    public void disableSendCodeAction() {
        stepTwoBinding.layoutPasswordRecoveryAction.setEnabled(false);
        stepTwoBinding.layoutPasswordRecoveryAction.setTextColor(ContextCompat.getColor(getCtx(), R.color.colorHint));
    }

    @Override
    public void showInvalidCodeError() {
        throw new NotImplementedException();
    }

    @Override
    public void showInvalidPasswordError() {

    }

    @Override
    public void showInvalidEmailError() {

    }

    @Override
    public void showMessage(int stringResId) {

    }

    @Override
    public void showMessage(String message) {

    }

    private static class ViewPagerAdapter extends PagerAdapter {
        private final List<View> views;

        ViewPagerAdapter() {
            views = new ArrayList<>(3);
        }

        void addView(View view) {
            if (views.size() == 3)
                throw new ArrayIndexOutOfBoundsException();

            views.add(view);
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
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
        void showMessage(int messageResId);
    }
}
