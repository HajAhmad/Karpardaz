package com.s.karpardaz.base.util.view;

import android.text.TextUtils;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class SnackbarUtil {

    void showSnackbar(View v, String text) {
        if (v != null && !TextUtils.isEmpty(text))
            Snackbar.make(v, text, Snackbar.LENGTH_SHORT).show();
    }

}
