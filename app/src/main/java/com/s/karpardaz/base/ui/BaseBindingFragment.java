package com.s.karpardaz.base.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import java.util.Objects;

public abstract class BaseBindingFragment<L, V extends ViewBinding> extends BaseFragment<L> {

    private V mBinding;

    @Nullable
    @Override
    public abstract View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState);

    @Override
    public final void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        onViewCreated(savedInstanceState);
    }

    protected abstract void onViewCreated(@Nullable Bundle savedInstanceState);

    @Override
    protected final int getLayout() {
        throw new RuntimeException("Invalid use of action in this context.");
    }

    @Override
    protected final View getRootView() {
        throw new RuntimeException("Using getRootView() is Prohibited in this context. Use " +
                "getBinding() instead.");
    }

    protected void setBinding(@NonNull V binding){
        mBinding = Objects.requireNonNull(binding);
    }

    @NonNull
    protected V getBinding(){
        return mBinding;
    }

    @Override
    protected void clearReferences() {
        super.clearReferences();
        mBinding = null;
    }
}