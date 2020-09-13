package com.s.karpardaz.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public abstract class BaseDialogFragment<L extends BaseInteractionListener, V> extends DialogFragment {

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
        mListener = Objects.requireNonNull(listener);
    }

    private void clearReferences() {
        mListener = null;
        mContext = null;
        mBinding = null;
    }

    protected L getListener() {
        Objects.requireNonNull(mListener, "Listener is NOT set in this context.");
        return mListener;
    }

    protected void setBinding(@NonNull V binding) {
        mBinding = Objects.requireNonNull(binding);
    }

    protected V getBinding() {
        return mBinding;
    }
}
