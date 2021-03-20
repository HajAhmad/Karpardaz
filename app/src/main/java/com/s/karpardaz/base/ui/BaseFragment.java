package com.s.karpardaz.base.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import static java.util.Objects.requireNonNull;


public abstract class BaseFragment<L> extends Fragment implements Progress {

    private View mRootView;
    private Context mContext;
    @Nullable
    private L mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @LayoutRes
    protected abstract int getLayout();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRootView = view;
    }

    @Override
    public void onStop() {
        super.onStop();
        clearReferences();
    }

    @Override
    public void showProgress(@Nullable String message) {
        ProgressDialog.getInstance(mContext).setMessage(message).show();
    }

    @Override
    public void showProgress(@StringRes int stringResId) {
        ProgressDialog.getInstance(mContext).setMessage(stringResId).show();
    }

    @Override
    public void hideProgress() {
        ProgressDialog.getInstance(mContext).dismiss();
    }

    protected View getRootView() {
        return mRootView;
    }

    protected Context getCtx() {
        return mContext;
    }

    public void setInteractionListener(@Nullable L listener) {
        mListener = listener;
    }

    protected void clearReferences() {
        mListener = null;
        mContext = null;
        mRootView = null;
    }

    @NonNull
    protected L getListener() {
        return requireNonNull(mListener, "Listener is NOT set in this context.");
    }

}
