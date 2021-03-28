package com.s.karpardaz.base.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import static java.util.Objects.requireNonNull;

public abstract class BaseDialogFragment<L extends BaseInteractionListener, V>
        extends DialogFragment {

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
    public abstract void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState);

    @Override
    public void onDestroy() {
        super.onDestroy();
        clearReferences();
    }

    public void setInteractionListener(@NonNull L listener) {
        mListener = requireNonNull(listener);
    }

    @CallSuper
    protected void clearReferences() {
        mListener = null;
        mContext = null;
        mBinding = null;
    }

    protected Context getCtx() {
        return mContext;
    }

    protected L getListener() {
        requireNonNull(mListener, "Listener has not been set in this context.");
        return mListener;
    }

    protected void setBinding(@NonNull V binding) {
        mBinding = requireNonNull(binding);
    }

    protected V getBinding() {
        return mBinding;
    }
}
