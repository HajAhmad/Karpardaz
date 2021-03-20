package com.s.karpardaz.base.ui;

public interface OnFinFragmentInteractionListener {

    <L extends OnFinFragmentInteractionListener, T extends BaseFinFragment<L>>
    void openAddFinDialog(T dialog);
}
