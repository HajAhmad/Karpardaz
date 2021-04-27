package com.s.karpardaz.user;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.s.karpardaz.R;
import com.s.karpardaz.base.ui.BaseFragment;
import com.s.karpardaz.base.ui.BaseInteractionListener;
import com.s.karpardaz.databinding.LayoutPasswordRecoveryBinding;
import com.s.karpardaz.databinding.LayoutPasswordRecoveryStepOneBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RecoveryPasswordFragment extends BaseFragment<RecoveryPasswordFragment.OnRecoveryPasswordInteractionListener,
    LayoutPasswordRecoveryBinding> implements RecoveryPasswordContract.View {

    @Inject
    RecoveryPasswordContract.Presenter mPresenter;

    private ViewPagerAdapter mAdapter;

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
        getBinding().layoutLoginPasswordRecoveryPagers.setOnTouchListener((v, event) -> false);
        getBinding().layoutLoginPasswordRecoveryPagers.setAdapter(mAdapter);

        mPresenter.start();
    }

    @Override
    public void initEmailView(){
        LayoutPasswordRecoveryStepOneBinding binding = LayoutPasswordRecoveryStepOneBinding.inflate(
            LayoutInflater.from(getCtx()), null, false
        );
        binding.layoutPasswordRecoveryAction.setOnClickListener(v -> {
            if (binding.layoutPasswordRecoveryInput.getText() != null)
                mPresenter.recoverPassword(binding.layoutPasswordRecoveryInput.getText().toString());
            else
                getListener().showMessage(R.string.all_enter_email_message);
        });
        mAdapter.addView(binding.getRoot());
    }


    @Override
    public void initCodeView() {

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
            ((ViewPager) container).removeView((View) object);
        }
    }

    public interface OnRecoveryPasswordInteractionListener extends BaseInteractionListener {
        void showMessage(int messageResId);
    }
}
