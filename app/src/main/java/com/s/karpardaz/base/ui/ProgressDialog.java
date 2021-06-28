package com.s.karpardaz.base.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.s.karpardaz.R;
import com.s.karpardaz.databinding.DialogProgressBinding;

public class ProgressDialog extends Dialog {

    private DialogProgressBinding mBinding;
    private String mMessage;

    public ProgressDialog(@NonNull Context context) {
        super(context, R.style.AppTheme_Dialog);
        setCancelable(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DialogProgressBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mBinding.dialogProgressMessage.setText(mMessage);
    }

    @Override
    public void onStart() {
        super.onStart();
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void dismiss() {
        mBinding = null;
        super.dismiss();
    }

    public void setMessage(@Nullable String message) {
        if (message != null)
            mMessage = message;
    }

    public void setMessage(@StringRes int stringResId) {
        mMessage = getContext().getString(stringResId);
    }

}
