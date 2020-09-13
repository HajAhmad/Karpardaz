package com.s.karpardaz.home.costlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.s.karpardaz.base.BaseBindingFragment;
import com.s.karpardaz.base.BaseInteractionListener;
import com.s.karpardaz.cost.data.model.Cost;
import com.s.karpardaz.databinding.FragmentCostListBinding;

import java.util.List;

import javax.inject.Inject;


public class CostListFragment extends
        BaseBindingFragment<CostListFragment.OnCostListFragmentInteractionListener, FragmentCostListBinding> {

    @Inject
    CostListViewModel mViewModel;

    public static CostListFragment newInstance() {
        return new CostListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        setBinding(FragmentCostListBinding.inflate(inflater, container, false));
        return getBinding().getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setTodayTotalCost();
        initList();
    }

    private void initList() {
        List<Cost> costList = mViewModel.getLastTwentyCosts();
    }

    private void setTodayTotalCost() {
        String todayTotalCost = mViewModel.getTodayTotalCost();
    }


    public interface OnCostListFragmentInteractionListener extends BaseInteractionListener {

    }

}
