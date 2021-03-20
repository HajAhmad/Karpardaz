package com.s.karpardaz.base.ui;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.s.karpardaz.R;

public interface Progress {

    default void showProgress() {
        showProgress(R.string.all_loading_message);
    }

    void showProgress(@StringRes int stringResId);

    void showProgress(@Nullable String message);

    void hideProgress();
}
