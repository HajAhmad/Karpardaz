package com.s.karpardaz.base.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import static com.s.karpardaz.base.util.view.SnackbarUtil.showSnackbar;


public abstract class BaseActivity<V> extends AppCompatActivity {

    private V mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearReferences();
    }

    protected void SnackbarMessage(String value) {
        showSnackbar(getBinding().getRoot(), value);
    }

    protected void clearReferences(){
        mBinding = null;
    }

    protected void setBinding(@NonNull V binding) {
        mBinding = Objects.requireNonNull(binding);
    }

    protected V getBinding() {
        return mBinding;
    }
}

