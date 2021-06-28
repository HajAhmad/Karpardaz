package com.s.karpardaz.base.ui;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import java.util.Objects;


public abstract class BaseActivity<V extends ViewBinding> extends AppCompatActivity implements
    Progress {

    private V mBinding;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onDestroy() {
        clearReferences();
        super.onDestroy();
    }

    @Override
    public void showProgress(int stringResId) {
        showProgress(getString(stringResId));
    }

    @Override
    public void showProgress(@NonNull String message) {
        if (mProgressDialog == null)
            mProgressDialog = new ProgressDialog(this);

        mProgressDialog.setMessage(message);

        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
        mProgressDialog = null;
    }

    protected void clearReferences() {
        hideProgress();
        mBinding = null;
    }

    protected void setBinding(@NonNull V binding) {
        mBinding = Objects.requireNonNull(binding);
    }

    protected View getRoot() {
        return mBinding.getRoot();
    }

    protected V getBinding() {
        return mBinding;
    }

}

