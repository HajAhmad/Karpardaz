package com.s.karpardaz.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

public abstract class BaseBindingFragment<L extends BaseInteractionListener, V>
        extends BaseFragment<L> {
    private V mBinding;

    @Override
    protected final int getLayout() {
        throw new RuntimeException("Invalid use of action in this context.");
    }

    @Nullable
    @Override
    public abstract View onCreateView(@NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState);

    @Override
    public abstract void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState);

    @Override
    protected final View getRootView() {
        throw new RuntimeException("Using getRootView() is Prohibited in this context. Use " +
                "getBinding() instead.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }

    protected void setBinding(@NonNull V binding) {
        mBinding = Objects.requireNonNull(binding);
    }

    protected V getBinding() {
        return mBinding;
    }
}
