package com.s.karpardaz.base.ui;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.s.karpardaz.R;

public interface Progress {

    default void showProgress() {
        showProgress(R.string.all_loading_message);
    }

    void showProgress(@StringRes int stringResId);

    void showProgress(@NonNull String message);

    void hideProgress();
}
