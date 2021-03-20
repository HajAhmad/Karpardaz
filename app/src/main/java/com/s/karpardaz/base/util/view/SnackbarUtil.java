package com.s.karpardaz.base.util.view;

import android.text.TextUtils;
import android.view.View;

import androidx.annotation.StringRes;

import com.google.android.material.snackbar.Snackbar;

public final class SnackbarUtil {

    private SnackbarUtil() {
    }

    public static void showSnackbar(final View v, final String text) {
        if (v != null && !TextUtils.isEmpty(text))
            Snackbar.make(v, text, Snackbar.LENGTH_SHORT).show();
    }

    public static void showSnackbar(final View v, @StringRes final int textResId) {
        if (v != null)
            Snackbar.make(v, textResId, Snackbar.LENGTH_SHORT).show();
    }

}
