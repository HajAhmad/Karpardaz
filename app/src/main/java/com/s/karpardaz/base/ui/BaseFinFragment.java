package com.s.karpardaz.base.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.s.karpardaz.databinding.FragmentFinListBinding;

public abstract class BaseFinFragment<L extends OnFinFragmentInteractionListener> extends
        BaseBindingFragment<L, FragmentFinListBinding> {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        setBinding(FragmentFinListBinding.inflate(inflater, container, false));
        return getBinding().getRoot();
    }


    @Override
    public void onViewCreated(@Nullable Bundle savedInstanceState) {
        setTodayTotalCost();
        initList();
        initView();
    }

    private void initView() {

    }

    private void initList() {
        getBinding().fragmentCostListAddAction.setOnClickListener(v -> getListener().openAddFinDialog(this));
    }

    private void setTodayTotalCost() {

    }

}
