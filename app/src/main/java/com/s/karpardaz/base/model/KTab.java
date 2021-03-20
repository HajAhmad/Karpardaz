package com.s.karpardaz.base.model;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

public class KTab {
    private final Fragment fragment;
    @StringRes
    private final int titleResId;

    public KTab(Fragment fragment, @StringRes int titleResId) {
        this.fragment = fragment;
        this.titleResId = titleResId;
    }

    public Fragment getFragment() {
        return fragment;
    }

    @StringRes
    public int getTitleResId() {
        return titleResId;
    }
}
