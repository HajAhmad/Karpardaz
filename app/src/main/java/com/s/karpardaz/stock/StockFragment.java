package com.s.karpardaz.stock;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.s.karpardaz.base.ui.BaseFragment;
import com.s.karpardaz.base.ui.BaseInteractionListener;
import com.s.karpardaz.databinding.FragmentStockBinding;
import com.s.karpardaz.stock.model.Stock;
import com.s.karpardaz.stock.model.StockListItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class StockFragment extends BaseFragment<StockFragment.OnStockFragmentInteractionListener, FragmentStockBinding>
    implements StockContract.View {

    @Inject
    StockContract.Presenter mPresenter;
    private StockListAdapter mAdapter;

    public static StockFragment newInstance() {
        return new StockFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.takeView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setBinding(FragmentStockBinding.inflate(inflater, container, false));
        return getBinding().getRoot();
    }

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {

        mPresenter.getAllStocks();

        getBinding().fragmentStockList.setItemAnimator(new DefaultItemAnimator());
        getBinding().fragmentStockList.addItemDecoration(new DividerItemDecoration(getCtx(), DividerItemDecoration.HORIZONTAL));
        getBinding().fragmentStockList.setLayoutManager(new LinearLayoutManager(getCtx()));

    }

    @Override
    public void showNoStock() {

    }

    @Override
    public void setStocks(List<Stock> result) {
        List<StockListItem> list = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            StockListItem item = list.get(i);
            list.add(new StockListItem(item.getId(), (i + 1), item.getName(), item.getCurrency(), item.isDefault()));
        }

        list.add(0, new StockListItem(StockListAdapter.TYPE_ADD_BUTTON));

        mAdapter = new StockListAdapter(this::openEditStockDialog, this::openAddStockDialog);

        getBinding().fragmentStockList.setAdapter(mAdapter);

    }

    private void openAddStockDialog() {

    }

    private void openEditStockDialog(StockListItem item, int position) {

    }

    public interface OnStockFragmentInteractionListener extends BaseInteractionListener {

    }

}
