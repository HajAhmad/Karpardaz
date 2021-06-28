package com.s.karpardaz.cost;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.s.karpardaz.base.ui.BaseFragment;
import com.s.karpardaz.base.ui.BaseInteractionListener;
import com.s.karpardaz.cost.model.CostViewItem;
import com.s.karpardaz.databinding.FragmentFinListBinding;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CostFragment extends BaseFragment<CostFragment.OnCostListFragmentInteractionListener,
    FragmentFinListBinding> implements CostContract.View, CostDetailDialog.OnCostDetailDialogInteractionListener {

    public static final String TAG = CostFragment.class.getSimpleName();
    private CostListAdapter mAdapter;

    public static CostFragment newInstance() {
        return new CostFragment();
    }

    @Inject
    CostContract.Presenter mPresenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.takeView(this);
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
        mPresenter.start();

        initView();
    }

    @Override
    protected void clearReferences() {
        mPresenter.dropView();
        mPresenter = null;
        super.clearReferences();
    }

    private void initView() {
        getBinding().fragmentCostListAddAction.setOnClickListener(v -> openAddCostDialog());

        mAdapter = new CostListAdapter((item, position) -> openCostDetailDialog(item.getId()));

        getBinding().fragmentCostListList.setLayoutManager(new LinearLayoutManager(getCtx()));
        getBinding().fragmentCostListList.setItemAnimator(new DefaultItemAnimator());
        getBinding().fragmentCostListList.addItemDecoration(new DividerItemDecoration(getCtx(), DividerItemDecoration.HORIZONTAL));
        getBinding().fragmentCostListList.setAdapter(mAdapter);
        getBinding().fragmentCostListList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 && getBinding().fragmentCostListAddAction.isShown())
                    getBinding().fragmentCostListAddAction.hide();
                else if (dy < 0 && !getBinding().fragmentCostListAddAction.isShown())
                    getBinding().fragmentCostListAddAction.show();
            }
        });
    }

    @Override
    public void setTodayTotalCost(String toString, String currency) {
        getBinding().fragmentCostListSum.setText(toString);
        getBinding().fragmentCostListSumCurrency.setText(currency);
    }

    @Override
    public void showTodayCostList(List<CostViewItem> result) {
        mAdapter.setItems(result);
    }

    private void openAddCostDialog() {
        openCostDetailDialog(null);
    }

    private void openCostDetailDialog(@Nullable String costId) {
        CostDetailDialog dialog = CostDetailDialog.newInstance(costId);
        dialog.setInteractionListener(this);
        dialog.show(getChildFragmentManager(), CostDetailDialog.TAG);
    }

    @Override
    public void updateList() {
        mPresenter.start();
    }

    public interface OnCostListFragmentInteractionListener extends BaseInteractionListener {

    }

}
