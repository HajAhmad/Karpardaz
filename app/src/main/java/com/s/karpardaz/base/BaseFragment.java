package com.s.karpardaz.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;


public abstract class BaseFragment<L extends BaseInteractionListener> extends Fragment {

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
    public void onDestroy() {
        super.onDestroy();
        clearReferences();
    }

    protected View getRootView() {
        return mRootView;
    }

    protected Context getBaseContext() {
        return mContext;
    }

    public void setInteractionListener(@NonNull L listener) {
        mListener = Objects.requireNonNull(listener);
    }

    private void clearReferences() {
        mListener = null;
        mContext= null;
    }

    protected L getListener() {
        Objects.requireNonNull(mListener, "Listener is NOT set in this context.");
        return mListener;
    }

}
