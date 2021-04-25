package com.s.karpardaz.base.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public abstract class BaseFragment<L extends BaseInteractionListener, V extends ViewBinding> extends Fragment
    implements Progress {

    private Context mContext;
    @Nullable
    private L mListener;
    private V mBinding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public abstract View onCreateView(@NonNull LayoutInflater inflater,
        @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState);

    @Override
    public final void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        onViewCreated(savedInstanceState);
    }

    protected abstract void onViewCreated(@Nullable Bundle savedInstanceState);

    @Override
    public void onStop() {
        super.onStop();
        clearReferences();
    }

    protected Context getCtx() {
        return mContext;
    }

    public void setInteractionListener(@Nullable L listener) {
        mListener = listener;
    }

    @NonNull
    protected L getListener() {
        return requireNonNull(mListener, "Listener is NOT set in this context.");
    }

    protected void setBinding(@NonNull V binding) {
        mBinding = Objects.requireNonNull(binding);
    }

    @NonNull
    protected V getBinding() {
        return mBinding;
    }

    protected View getRoot() {
        return mBinding.getRoot();
    }

    protected void clearReferences() {
        mListener = null;
        mContext = null;
        mBinding = null;
    }

    @Override
    public void showProgress(int stringResId) {
        mListener.showProgress(stringResId);
    }

    @Override
    public void showProgress(@NonNull String message) {
        mListener.showProgress(message);
    }

    @Override
    public void hideProgress() {
        mListener.hideProgress();
    }

}