package com.s.karpardaz.base.ui;

import androidx.annotation.StringRes;

public interface BaseInteractionListener extends Progress{
    void showMessage(String string);
    void showMessage(@StringRes int stringRes);
}
