package com.s.karpardaz.base.util.view;

import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import static java.util.Objects.requireNonNull;

public final class AlertUtil {

    private AlertUtil() {
    }

    public static void showToast(@NonNull final View v, String text) {
        requireNonNull(v);
        if (TextUtils.isEmpty(text))
            text = "unknown message.";
        Toast.makeText(v.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(@NonNull View v, @StringRes int textResId) {
        requireNonNull(v);
        Toast.makeText(v.getContext(), textResId, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(@NonNull View v, @StringRes int textResId) {
        requireNonNull(v);
        Toast.makeText(v.getContext(), textResId, Toast.LENGTH_LONG).show();
    }

}
