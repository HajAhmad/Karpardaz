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

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class StockFragment extends BaseFragment<StockFragment.OnStockFragmentInteractionListener, FragmentStockBinding>
    implements StockContract.View, StockDetailDialog.StockDetailInteractionListener {

    public static final String TAG = StockFragment.class.getSimpleName();

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

        mAdapter = new StockListAdapter(this::openEditStockDialog, this::openAddStockDialog);

        getBinding().fragmentStockList.setItemAnimator(new DefaultItemAnimator());
        getBinding().fragmentStockList.addItemDecoration(new DividerItemDecoration(getCtx(), DividerItemDecoration.HORIZONTAL));
        getBinding().fragmentStockList.setLayoutManager(new LinearLayoutManager(getCtx()));
        getBinding().fragmentStockList.setAdapter(mAdapter);

        mPresenter.start();

    }

    @Override
    public void showNoStock() {
        mAdapter.setItems(new ArrayList<StockListItem>() {{
            add(new StockListItem(StockListAdapter.TYPE_ADD_BUTTON));
        }});
    }

    @Override
    public void setStocks(List<Stock> result, String defaultStockId) {
        List<StockListItem> list = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            Stock item = result.get(i);

            list.add(new StockListItem(
                item.getUuid(),
                i + 1,
                item.getName(),
                item.getCurrency(),
                item.getUuid().equals(defaultStockId)
            ));
        }

        list.add(0, new StockListItem(StockListAdapter.TYPE_ADD_BUTTON));

        mAdapter.setItems(list);
    }

    @Override
    protected void clearReferences() {
        mPresenter.dropView();
        mPresenter = null;
        super.clearReferences();
    }

    private void openAddStockDialog() {
        StockDetailDialog fragment = StockDetailDialog.newInstanceForInsertion();
        fragment.setInteractionListener(this);
        fragment.show(getChildFragmentManager(), StockDetailDialog.TAG_NEW);
    }

    private void openEditStockDialog(StockListItem item, int __) {
        StockDetailDialog fragment = StockDetailDialog.newInstanceForUpdate(item.getId());
        fragment.setInteractionListener(this);
        fragment.show(getChildFragmentManager(), StockDetailDialog.TAG_UPDATE);
    }

    @Override
    public void updateStockList() {
        mPresenter.getAllStocks();
        getListener().enableAllMenuOptionsIfDisabled();
    }

    public interface OnStockFragmentInteractionListener extends BaseInteractionListener {

        void enableAllMenuOptionsIfDisabled();
    }

}
