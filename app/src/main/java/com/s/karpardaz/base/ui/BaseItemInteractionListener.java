package com.s.karpardaz.base.ui;

import androidx.annotation.NonNull;

public interface BaseItemInteractionListener<T> {
    void onItemClicked(@NonNull T item, int position);
}
