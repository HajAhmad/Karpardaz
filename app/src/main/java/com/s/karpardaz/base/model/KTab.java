package com.s.karpardaz.base.model;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

public class KTab {
    private final Fragment fragment;
    @StringRes
    private final int titleResId;
    private final String tag;

    public KTab(Fragment fragment, @StringRes int titleResId, String tag) {
        this.fragment = fragment;
        this.titleResId = titleResId;
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public Fragment getFragment() {
        return fragment;
    }

    @StringRes
    public int getTitleResId() {
        return titleResId;
    }
}
