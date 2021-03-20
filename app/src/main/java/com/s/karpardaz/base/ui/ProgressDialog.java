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

    private static ProgressDialog sInstance;

    public static ProgressDialog getInstance(@NonNull Context context) {
        if (sInstance == null)
            sInstance = new ProgressDialog(context);

        return sInstance;
    }

    private ProgressDialog(@NonNull Context context) {
        super(context, R.style.AppTheme_FullScreenDialog);
        setCancelable(false);
    }

    public ProgressDialog setMessage(@Nullable String message) {
        if (message != null)
            mBinding.dialogProgressMessage.setText(message);
        return this;
    }

    public ProgressDialog setMessage(@StringRes int stringResId){
        mBinding.dialogProgressMessage.setText(stringResId);
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DialogProgressBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }

    @Override
    public void onStart() {
        super.onStart();
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        getWindow().setWindowAnimations(R.style.AppTheme_Slide);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sInstance = null;
    }
}
