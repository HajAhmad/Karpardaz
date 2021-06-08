package com.s.karpardaz.base;

import android.text.Editable;
import android.text.TextWatcher;

@FunctionalInterface
public interface KTextWatcher extends TextWatcher {
    @Override
    default void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    default void onTextChanged(CharSequence s, int start, int before, int count) {
        onTextChanged(s, count);
    }

    void onTextChanged(CharSequence s, int count);

    @Override
    default void afterTextChanged(Editable s) {}
}
