package com.s.karpardaz.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.s.karpardaz.base.BaseBindingFragment;
import com.s.karpardaz.base.BaseInteractionListener;
import com.s.karpardaz.shared.data.model.Cost;
import com.s.karpardaz.databinding.FragmentHomeBinding;

import java.util.List;

public class HomeFragment extends
        BaseBindingFragment<HomeFragment.OnCostListFragmentInteractionListener, FragmentHomeBinding> {

    public static final String TAG = HomeFragment.class.getSimpleName();

    private CostListViewModel mViewModel;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CostListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        setBinding(FragmentHomeBinding.inflate(inflater, container, false));
        return getBinding().getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setTodayTotalCost();
        initList();
        initView();
    }

    private void initView() {
        getBinding().fragmentCostListAddAction.setOnClickListener(v ->
                getListener().openAddCostFragment());
    }


    private void initList() {
        LiveData<List<Cost>> costList = mViewModel.getCostListObservable();
        HomeCostListAdapter adapter = new HomeCostListAdapter();
    }

    private void setTodayTotalCost() {
        mViewModel.weekTotalSum.observe(getViewLifecycleOwner(),
                getBinding().fragmentCostListSum::setText);
    }


    public interface OnCostListFragmentInteractionListener extends BaseInteractionListener {
        void openAddCostFragment();
    }

}
